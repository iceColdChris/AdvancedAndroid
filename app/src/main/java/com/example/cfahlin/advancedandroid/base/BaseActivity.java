package com.example.cfahlin.advancedandroid.base;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.cfahlin.advancedandroid.di.Injector;

import java.util.UUID;

public abstract class BaseActivity extends AppCompatActivity{

	private static String INSTANCE_ID_KEY = "instance_id";

	private String instanceID;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		if(savedInstanceState != null)
			instanceID = savedInstanceState.getString(INSTANCE_ID_KEY);
		else
			instanceID = UUID.randomUUID().toString();

		Injector.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(INSTANCE_ID_KEY, instanceID);
	}

	public String getInstanceID() {
		return instanceID;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(isFinishing())
			Injector.clearComponent(this);
	}
}
