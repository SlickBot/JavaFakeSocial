package com.ulj.slicky.javafakesocial.activity.about;

import android.os.Bundle;
import android.view.View;

import com.ulj.slicky.javafakesocial.FakeUtils;
import com.ulj.slicky.javafakesocial.R;
import com.ulj.slicky.javafakesocial.activity.BackableActivity;
import com.ulj.slicky.javafakesocial.rest.ApiServices;

/**
 * Created by SlickyPC on 22.5.2017
 */
public class AboutActivity extends BackableActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
    }

    public void onRandomUserClick(View view) {
        FakeUtils.startBrowseActivity(this, ApiServices.PERSON_URL);
    }

    public void onWatchOutClick(View view) {
        FakeUtils.startBrowseActivity(this, ApiServices.CONTENT_URL);
    }
}
