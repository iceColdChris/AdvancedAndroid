package com.example.cfahlin.advancedandroid.base;

import com.example.cfahlin.advancedandroid.data.RepoServiceModule;
import com.example.cfahlin.advancedandroid.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {
		ApplicationModule.class,
		ActivityBindingModule.class,
		ServiceModule.class,
		RepoServiceModule.class,
})
public interface ApplicationComponent {
	void inject(AdvancedApplication advancedApplication);
}
