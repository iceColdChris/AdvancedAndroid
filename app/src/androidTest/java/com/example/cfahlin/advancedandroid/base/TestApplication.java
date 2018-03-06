package com.example.cfahlin.advancedandroid.base;

import android.support.test.InstrumentationRegistry;

public class TestApplication extends AdvancedApplication {
	@Override
	protected ApplicationComponent initComponent() {
		return DaggerTestApplicationComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.build();
	}

	public static TestApplicationComponent getComponent() {
		return (TestApplicationComponent) ((TestApplication)InstrumentationRegistry.getTargetContext().getApplicationContext()).component;
	}
}
