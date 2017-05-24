package com.slicky.ulj.javafakesocial.activity.login;

import android.content.Intent;
import com.slicky.ulj.javafakesocial.activity.ProgressDialogTask;
import com.slicky.ulj.javafakesocial.activity.content.ContentActivity;
import com.slicky.ulj.javafakesocial.rest.DummyDBHandler;

import java.io.IOException;

/**
 * Created by SlickyPC on 21.5.2017
 */
class SignUpTask extends ProgressDialogTask<Boolean> {

    private final SignUpFragment fragment;

    private final String first;
    private final String last;
    private final String email;
    private final String password;

    SignUpTask(SignUpFragment fragment,
                      String first,
                      String last,
                      String email,
                      String password) {
        super(fragment.getContext(), "Signing Up...");

        this.fragment = fragment;
        this.first = first;
        this.last = last;
        this.email = email;
        this.password = password;
    }

    @Override
    public Boolean backgroundTask() throws IOException {
        return DummyDBHandler.getInstance().signup(first, last, email, password);
    }

    @Override
    public void success(Boolean canSignup) {
        if (canSignup) {
            Intent intent = new Intent(fragment.getActivity(), ContentActivity.class);
            fragment.startActivity(intent);
            fragment.getActivity().finish();
        } else {
            fragment.failSignup();
        }
    }

    @Override
    public void fail(Exception e) {
        fragment.handleError("Could not Sign Up!", e);
    }
}
