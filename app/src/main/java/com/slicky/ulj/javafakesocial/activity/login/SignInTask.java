package com.slicky.ulj.javafakesocial.activity.login;

import com.slicky.ulj.javafakesocial.activity.ProgressDialogTask;
import com.slicky.ulj.javafakesocial.rest.DummyDBHandler;

import java.io.IOException;

/**
 * Created by SlickyPC on 21.5.2017
 */
class SignInTask extends ProgressDialogTask<Boolean> {

    private final SignInFragment fragment;

    private final String email;
    private final String password;

    SignInTask(SignInFragment fragment,
                      String email,
                      String password) {
        super(fragment.getContext(), "Signing In...");

        this.fragment = fragment;
        this.email = email;
        this.password = password;
    }

    @Override
    public Boolean backgroundTask() throws IOException {
        return DummyDBHandler.getInstance().signin(email, password);
    }

    @Override
    public void success(Boolean canLogin) {
        if (canLogin)
            fragment.successSignin();
        else
            fragment.failSignin("Invalid User data!", null);
    }

    @Override
    public void fail(Exception e) {
        fragment.failSignin("Could not Sign In!", e);
    }
}
