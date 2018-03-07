package com.example.cfahlin.advancedandroid.data;

import com.example.cfahlin.advancedandroid.model.Contributor;
import com.example.cfahlin.advancedandroid.model.Repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class RepoRepository {

    private final Provider<RepoRequester> repoRequesterProvider;
    private final Scheduler scheduler;
    private final List<Repo> cachedTrendingRepos = new ArrayList<>();
    private final Map<String, List<Contributor>> cachedContributors = new HashMap<>();

    @Inject
    RepoRepository(Provider<RepoRequester> repoRequesterProvider,
                   @Named("network_scheduler")Scheduler scheduler){
        this.repoRequesterProvider = repoRequesterProvider;
        this.scheduler = scheduler;
    }

    public Single<List<Repo>> getTrendingRepos() {
        return Maybe.concat(cachedTrendingRepos(), apiTrendingRepos())
                .firstOrError()
                .subscribeOn(scheduler);
    }

    public Single<Repo> getRepo(String repoOwner, String repoName) {
        return Maybe.concat(cachedRepo(repoOwner, repoName), apiRepo(repoOwner, repoName))
                .firstOrError()
                .subscribeOn(scheduler);
    }

    public Single<List<Contributor>> getContributors(String url) {
        return Maybe.concat(cachedContributors(url), apiContributors(url))
                .firstOrError()
                .subscribeOn(scheduler);
    }

    private Maybe<List<Contributor>> cachedContributors(String url) {
        return Maybe.create(emitter -> {
            if(cachedContributors.containsKey(url))
                emitter.onSuccess(cachedContributors.get(url));
            emitter.onComplete();
        });
    }

    private Maybe<List<Contributor>> apiContributors(String url) {
        return repoRequesterProvider.get().getContributors(url)
                .doOnSuccess(contributors -> cachedContributors.put(url, contributors))
                .toMaybe();
    }
    private Maybe<Repo> cachedRepo(String repoOwner, String repoName) {
        return Maybe.create(emitter -> {
            for (Repo cachedRepo: cachedTrendingRepos) {
                if(cachedRepo.owner().login().equals(repoOwner)
                        && cachedRepo.name().equals(repoName)) {
                    emitter.onSuccess(cachedRepo);
                    break;
                }
            }
            emitter.onComplete();
        });
    }

    private Maybe<Repo> apiRepo(String repoOwner, String repoName) {
        return repoRequesterProvider.get().getRepo(repoOwner, repoName)
                .toMaybe();
    }

    private Maybe<List<Repo>> cachedTrendingRepos() {
        return Maybe.create(emitter -> {
            if(!cachedTrendingRepos.isEmpty())
                emitter.onSuccess(cachedTrendingRepos);
            emitter.onComplete();
        });
    }

    private Maybe<List<Repo>> apiTrendingRepos() {
        return repoRequesterProvider.get().getTrendingRepos()
                .doOnSuccess(repos -> {
                    cachedTrendingRepos.clear();
                    cachedTrendingRepos.addAll(repos);
                })
                .toMaybe();
    }
}
