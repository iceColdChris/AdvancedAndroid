package com.example.cfahlin.advancedandroid.trending;

import com.example.cfahlin.advancedandroid.data.RepoRepository;
import com.example.cfahlin.advancedandroid.data.RepoRequester;
import com.example.cfahlin.advancedandroid.di.ScreenScope;
import com.example.cfahlin.advancedandroid.model.Repo;
import com.example.cfahlin.advancedandroid.ui.ScreenNavigator;

import javax.inject.Inject;

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {
	private final TrendingReposViewModel viewModel;
	private final RepoRepository repoRepository;
	private final ScreenNavigator screenNavigator;

	@Inject
	TrendingReposPresenter(TrendingReposViewModel viewModel,
						   RepoRepository repoRepository,
						   ScreenNavigator screenNavigator) {
		this.viewModel = viewModel;
		this.repoRepository = repoRepository;
		this.screenNavigator = screenNavigator;
		loadRepos();
	}

	private void loadRepos() {
		repoRepository.getTrendingRepos()
				.doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
				.doOnEvent((d,t) -> viewModel.loadingUpdated().accept(false))
				.subscribe(viewModel.reposUpdated(), viewModel.onError());
	}

	@Override
	public void onRepoClicked(Repo repo) {
		screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
	}
}
