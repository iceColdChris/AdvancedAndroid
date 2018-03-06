package com.example.cfahlin.advancedandroid.base;

import android.app.Application;

import com.example.cfahlin.advancedandroid.BuildConfig;
import com.example.cfahlin.advancedandroid.di.ActivityInjector;

import javax.inject.Inject;

import timber.log.Timber;


public class AdvancedApplication extends Application {

	@Inject ActivityInjector activityInjector;

	protected ApplicationComponent component;

	@Override
	public void onCreate() {
		super.onCreate();

		component = initComponent();
		component.inject(this);

		if(BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		}
	}

	protected ApplicationComponent initComponent() {
		return DaggerApplicationComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.build();
	}

	public ActivityInjector getActivityInjector() {
		return activityInjector;
	}
}
