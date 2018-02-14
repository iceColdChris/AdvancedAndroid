package com.example.cfahlin.advancedandroid.base;

import android.app.Application;

import com.example.cfahlin.advancedandroid.di.ActivityInjector;

import javax.inject.Inject;


public class AdvancedApplication extends Application {

	@Inject ActivityInjector activityInjector;

	private ApplicationComponent component;

	@Override
	public void onCreate() {
		super.onCreate();

		component = DaggerApplicationComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.build();

		component.inject(this);
	}

	public ActivityInjector getActivityInjector() {
		return activityInjector;
	}
}
