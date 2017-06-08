package com.slicky.ulj.javafakesocial.activity.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by SlickyPC on 18.5.2017
 */
class LoginAdapter extends FragmentPagerAdapter {

    private final SignInFragment signinFragment;
    private final SignUpFragment signupFragment;

    LoginAdapter(FragmentManager fm) {
        super(fm);

        signinFragment = SignInFragment.newInstance();
        signupFragment = SignUpFragment.newInstance();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return signupFragment;
            default:
                return signinFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
