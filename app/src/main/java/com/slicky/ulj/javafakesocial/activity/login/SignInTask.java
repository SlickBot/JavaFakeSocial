package com.slicky.ulj.javafakesocial.activity.login;

import android.content.Intent;
import com.slicky.ulj.javafakesocial.activity.ProgressDialogTask;
import com.slicky.ulj.javafakesocial.activity.content.ContentActivity;
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
        super(fragment.getContext(), "Logging In...");

        this.fragment = fragment;
        this.email = email;
        this.password = password;
    }

    @Override
    public Boolean backgroundTask() throws IOException {
        return DummyDBHandler.getInstance().login(email, password);
    }

    @Override
    public void success(Boolean canLogin) {
        if (canLogin) {
            Intent intent = new Intent(fragment.getContext(), ContentActivity.class);
            fragment.startActivity(intent);
            fragment.getActivity().finish();
        } else {
            fragment.failLogin();
        }
    }

    @Override
    public void fail(Exception e) {
        fragment.handleError("Could not Log In!", e);
    }
}
