package com.example.cfahlin.advancedandroid.details;

import com.example.cfahlin.advancedandroid.data.RepoRepository;
import com.example.cfahlin.advancedandroid.di.ScreenScope;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;

@ScreenScope
public class RepoDetailsPresenter {

    @Inject
    RepoDetailsPresenter(
            @Named("repo_owner") String repoOwner,
            @Named("repo_name") String repoName,
            RepoRepository repoRepository,
            RepoDetailsViewModel viewModel) {
        repoRepository.getRepo(repoOwner, repoName)
                .doOnSuccess(viewModel.processRepo())
                .doOnError(viewModel.detailsError())
                .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl()))
                .doOnError(viewModel.contributorsError())
                .subscribe(viewModel.processContributors(), throwable -> {
                    //We Handle logging in view model
                });
    }
}
