package com.example.cfahlin.advancedandroid.ui;


import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

public interface ScreenNavigator {

	void initWithRouter(Router router, Controller controller);
	boolean pop();

	void goToRepoDetails(String repoOwner, String repoName);

	void clear();

}
