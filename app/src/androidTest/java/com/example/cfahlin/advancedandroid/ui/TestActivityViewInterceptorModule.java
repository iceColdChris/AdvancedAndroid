package com.example.cfahlin.advancedandroid.ui;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class TestActivityViewInterceptorModule {
	@Provides
	static ActivityViewInterceptor provideActivityViewInterceptor() {
		return ActivityViewInterceptor.DEFAULT;
	}

}
