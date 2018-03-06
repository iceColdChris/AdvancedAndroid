package com.example.cfahlin.advancedandroid.base;

import android.app.Activity;

import com.example.cfahlin.advancedandroid.home.MainActivity;
import com.example.cfahlin.advancedandroid.home.TestMainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = TestMainActivityComponent.class)
public abstract class TestActivityBindingModule {

	@Binds
	@IntoMap
	@ActivityKey(MainActivity.class)
	abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjector(TestMainActivityComponent.Builder builder);
}
