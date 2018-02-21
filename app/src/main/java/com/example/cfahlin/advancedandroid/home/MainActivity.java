package com.example.cfahlin.advancedandroid.home;

import com.bluelinelabs.conductor.Controller;
import com.example.cfahlin.advancedandroid.R;
import com.example.cfahlin.advancedandroid.base.BaseActivity;
import com.example.cfahlin.advancedandroid.trending.TrendingReposController;

public class MainActivity extends BaseActivity {

	@Override
	protected int layoutRes() {
		return R.layout.activity_main;
	}

	@Override
	protected Controller initialScreen() {
		return new TrendingReposController();
	}
}
