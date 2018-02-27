package com.example.cfahlin.advancedandroid.trending;

import com.example.cfahlin.advancedandroid.data.RepoRequester;
import com.example.cfahlin.advancedandroid.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
class TrendingReposPresenter {
	private final TrendingReposViewModel viewModel;
	private final RepoRequester repoRequester;

	@Inject
	TrendingReposPresenter(TrendingReposViewModel viewModel, RepoRequester repoRequester) {
		this.viewModel = viewModel;
		this.repoRequester = repoRequester;
		loadRepos();
	}

	private void loadRepos() {
		repoRequester.getTrendingRepos()
				.doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
				.doOnEvent((d,t) -> viewModel.loadingUpdated().accept(false))
				.subscribe(viewModel.reposUpdated(), viewModel.onError());
	}
}
