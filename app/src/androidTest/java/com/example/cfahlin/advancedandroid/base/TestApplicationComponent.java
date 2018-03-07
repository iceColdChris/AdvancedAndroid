package com.example.cfahlin.advancedandroid.base;

import com.example.cfahlin.advancedandroid.data.RepoRepository;
import com.example.cfahlin.advancedandroid.data.TestRepoService;
import com.example.cfahlin.advancedandroid.data.TestRepoServiceModule;
import com.example.cfahlin.advancedandroid.networking.ServiceModule;
import com.example.cfahlin.advancedandroid.trending.TrendingReposControllerTest;
import com.example.cfahlin.advancedandroid.ui.NavigationModule;
import com.example.cfahlin.advancedandroid.ui.TestNavigationModule;
import com.example.cfahlin.advancedandroid.ui.TestScreenNavigator;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		ApplicationModule.class,
		TestActivityBindingModule.class,
		TestRepoServiceModule.class,
		ServiceModule.class,
		TestNavigationModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent{
	void inject(TrendingReposControllerTest trendingReposControllerTest);
	TestScreenNavigator screenNavigator();
	TestRepoService repoService();
	RepoRepository repoRepository();
}
