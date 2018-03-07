package com.example.cfahlin.advancedandroid.home;


import com.bluelinelabs.conductor.Controller;
import com.example.cfahlin.advancedandroid.details.RepoDetailsComponent;
import com.example.cfahlin.advancedandroid.details.RepoDetailsController;
import com.example.cfahlin.advancedandroid.di.ControllerKey;
import com.example.cfahlin.advancedandroid.trending.TrendingReposComponent;
import com.example.cfahlin.advancedandroid.trending.TrendingReposController;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
		TrendingReposComponent.class,
		RepoDetailsComponent.class,
})
public abstract class TestScreenBindingModule {

	@Binds
	@IntoMap
	@ControllerKey(TrendingReposController.class)
	abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(TrendingReposComponent.Builder builder);


	@Binds
	@IntoMap
	@ControllerKey(RepoDetailsController.class)
	abstract AndroidInjector.Factory<? extends Controller> bindRepoDetailsInjector(RepoDetailsComponent.Builder builder);

}
