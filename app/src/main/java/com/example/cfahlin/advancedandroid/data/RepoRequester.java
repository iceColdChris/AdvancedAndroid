package com.example.cfahlin.advancedandroid.data;


import com.example.cfahlin.advancedandroid.model.Repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class RepoRequester {

	private final RepoService service;

	@Inject
	RepoRequester(RepoService service) {

		this.service = service;
	}

	Single<List<Repo>> getTrendingRepo(){

		return service.getTrendingRepos()
				.map(TrendingReposResponse::repos)
				.subscribeOn(Schedulers.io());
	}
}
