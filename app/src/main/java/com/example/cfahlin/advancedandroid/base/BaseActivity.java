package com.example.cfahlin.advancedandroid.base;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.example.cfahlin.advancedandroid.R;
import com.example.cfahlin.advancedandroid.di.Injector;
import com.example.cfahlin.advancedandroid.di.ScreenInjector;
import com.example.cfahlin.advancedandroid.ui.ActivityViewInterceptor;
import com.example.cfahlin.advancedandroid.ui.ScreenNavigator;

import java.util.UUID;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity{

	private static String INSTANCE_ID_KEY = "instance_id";

	@Inject ScreenInjector screenInjector;
	@Inject ScreenNavigator screenNavigator;
	@Inject ActivityViewInterceptor activityViewInterceptor;

	private String instanceID;
	private Router router;


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		if(savedInstanceState != null)
			instanceID = savedInstanceState.getString(INSTANCE_ID_KEY);
		else
			instanceID = UUID.randomUUID().toString();

		Injector.inject(this);
		activityViewInterceptor.setContentView(this, layoutRes());

		ViewGroup screenContainer = findViewById(R.id.screen_container);

		if(screenContainer == null)
			throw new NullPointerException("Activity must have a view with id: screen_container");

		router = Conductor.attachRouter(this, screenContainer, savedInstanceState);
		screenNavigator.initWithRouter(router, initialScreen());
		monitorBackStack();
		super.onCreate(savedInstanceState);
	}




	@LayoutRes
	protected abstract int layoutRes();

	protected abstract Controller initialScreen();

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(INSTANCE_ID_KEY, instanceID);
	}

	@Override
	public void onBackPressed() {
		if(!screenNavigator.pop()) {
			super.onBackPressed();
		}
	}

	public String getInstanceID() {
		return instanceID;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		screenNavigator.clear();
		if(isFinishing())
			Injector.clearComponent(this);

		activityViewInterceptor.clear();
	}

	public ScreenInjector getScreenInjector() {
		return screenInjector;
	}

	private void monitorBackStack() {
		router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
			@Override
			public void onChangeStarted(@Nullable Controller to,
			                            @Nullable Controller from,
			                            boolean isPush,
			                            @NonNull ViewGroup container,
			                            @NonNull ControllerChangeHandler handler) {

			}

			@Override
			public void onChangeCompleted(@Nullable Controller to,
			                              @Nullable Controller from,
			                              boolean isPush,
			                              @NonNull ViewGroup container,
			                              @NonNull ControllerChangeHandler handler) {
				if( !isPush && from != null)
					Injector.clearComponent(from);

			}
		});
	}
}
