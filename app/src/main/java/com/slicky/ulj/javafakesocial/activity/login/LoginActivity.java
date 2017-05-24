package com.slicky.ulj.javafakesocial.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.rd.PageIndicatorView;
import com.slicky.ulj.javafakesocial.R;

/**
 * Created by SlickyPC on 17.5.2017
 */
public class LoginActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PagerAdapter adapter = new LoginAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        PageIndicatorView mPageIndicator = (PageIndicatorView) findViewById(R.id.indicator);
        mPageIndicator.setViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() > 0)
            viewPager.setCurrentItem(0);
        else
            super.onBackPressed();
    }

    public void onSignUpClick(View view) {
        viewPager.setCurrentItem(1);
    }

    public void onBackClick(View view) {
        viewPager.setCurrentItem(0);
    }

    public void onLostDetailsClick(View view) {
        Toast.makeText(this, "Well, that sucks.", Toast.LENGTH_SHORT).show();
    }
}
