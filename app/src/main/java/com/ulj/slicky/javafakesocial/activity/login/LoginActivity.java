package com.ulj.slicky.javafakesocial.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.rd.PageIndicatorView;
import com.ulj.slicky.javafakesocial.R;

/**
 * Created by SlickyPC on 17.5.2017
 */
public class LoginActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.login_container);
        viewPager.setAdapter(adapter);

        PageIndicatorView mPageIndicator = findViewById(R.id.login_indicator);
        mPageIndicator.setViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() > 0)
            viewPager.setCurrentItem(0);
        else
            super.onBackPressed();
    }

    public void onMoveToSignUpClick(View view) {
        viewPager.setCurrentItem(1);
    }

    public void onMoveToSignInClick(View view) {
        viewPager.setCurrentItem(0);
    }

    public void onLostDetailsClick(View view) {
        Toast.makeText(this, "Well, that sucks.", Toast.LENGTH_SHORT).show();
    }

}
