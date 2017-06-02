package com.slicky.ulj.javafakesocial.activity.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.slicky.ulj.javafakesocial.R;
import com.slicky.ulj.javafakesocial.activity.BackableActivity;
import com.slicky.ulj.javafakesocial.rest.content.ContentService;
import com.slicky.ulj.javafakesocial.rest.person.PersonService;

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
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(PersonService.URL));
        startActivity(browserIntent);
    }

    public void onWatchOutClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ContentService.URL));
        startActivity(browserIntent);
    }
}
