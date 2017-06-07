package com.slicky.ulj.javafakesocial.activity.login;

import com.slicky.ulj.javafakesocial.activity.ProgressDialogTask;
import com.slicky.ulj.javafakesocial.db.FakeDBHandler;

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
        return FakeDBHandler.getInstance().signup(first, last, email, password);
    }

    @Override
    public void success(Boolean canSignup) {
        if (canSignup)
            fragment.successSignup();
        else
            fragment.failSignup("Could not create new account!", null);
    }

    @Override
    public void fail(Exception e) {
        fragment.failSignup("Could not Sign Up!", e);
    }
}
