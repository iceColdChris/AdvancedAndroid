package com.example.cfahlin.advancedandroid.trending;

import com.example.cfahlin.advancedandroid.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface TrendingReposComponent  extends AndroidInjector<TrendingReposController> {

	@Subcomponent.Builder
	abstract class Builder extends AndroidInjector.Builder<TrendingReposController> {

	}
}
