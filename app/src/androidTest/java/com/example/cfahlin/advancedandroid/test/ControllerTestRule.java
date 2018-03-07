package com.example.cfahlin.advancedandroid.test;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import com.example.cfahlin.advancedandroid.base.TestApplication;
import com.example.cfahlin.advancedandroid.data.RepoRepository;
import com.example.cfahlin.advancedandroid.data.TestRepoService;
import com.example.cfahlin.advancedandroid.ui.TestScreenNavigator;

public class ControllerTestRule<T extends Activity> extends ActivityTestRule<T> {

    public final TestScreenNavigator screenNavigator;
    public final TestRepoService repoService;
    public final RepoRepository repoRepository;

    public ControllerTestRule(Class<T> activityClass) {
        super(activityClass, true, false);

        screenNavigator = TestApplication.getComponent().screenNavigator();
        repoRepository = TestApplication.getComponent().repoRepository();
        repoService = TestApplication.getComponent().repoService();
    }

    public void clearState() {
        repoService.clearErrorFlags();
        repoService.clearHoldFlags();
        repoRepository.clearCache();
    }
}
