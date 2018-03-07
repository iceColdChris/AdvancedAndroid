package com.example.cfahlin.advancedandroid.ui;


import com.example.cfahlin.advancedandroid.di.ActivityScope;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NavigationModule {

	@Binds
	@ActivityScope
	abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);

}
