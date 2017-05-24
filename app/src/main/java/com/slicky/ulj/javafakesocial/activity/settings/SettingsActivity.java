package com.slicky.ulj.javafakesocial.activity.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.slicky.ulj.javafakesocial.R;
import com.slicky.ulj.javafakesocial.activity.BackableActivity;

/**
 * Created by SlickyPC on 22.5.2017
 */
public class SettingsActivity extends BackableActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

}
