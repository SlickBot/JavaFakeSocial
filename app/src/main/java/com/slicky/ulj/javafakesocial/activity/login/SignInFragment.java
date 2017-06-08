package com.slicky.ulj.javafakesocial.activity.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.slicky.ulj.javafakesocial.FakeUtils;
import com.slicky.ulj.javafakesocial.R;
import com.slicky.ulj.javafakesocial.activity.content.ContentActivity;

import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;

/**
 * Created by SlickyPC on 18.5.2017
 */
public class SignInFragment extends Fragment {
    private static final String TAG = SignInFragment.class.getCanonicalName();

    private EditText emailField;
    private EditText passwordField;

    private SignInTask task;

    static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin_fragment, container, false);

        emailField = (EditText) view.findViewById(R.id.signin_email);
        passwordField = (EditText) view.findViewById(R.id.signin_password);

        passwordField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == IME_ACTION_DONE) {
                    trySignin();
                    return true;
                }
                return false;
            }
        });

        Button signInButton = (Button) view.findViewById(R.id.signin_signin_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trySignin();
            }
        });

        // TODO: This should be changed.
        emailField.setText("change@me.pls");
        passwordField.setText("password");

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (task != null)
            task.cancel();
    }

    private void trySignin() {
        SignInValidator validator = new SignInValidator(getView());
        if (validator.validate()) {
            task = new SignInTask(this,
                    emailField.getText().toString(),
                    passwordField.getText().toString());
            task.execute();
        } else {
            shakeStage();
        }
    }

    void successSignin() {
        Intent intent = new Intent(getContext(), ContentActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    void failSignin(String text, Exception e) {
        displayDialog(text + (e != null ? "\n" + e.getLocalizedMessage() : ""));
        shakeStage();
        Log.wtf(TAG, text, e);
    }

    private void shakeStage() {
        FakeUtils.shakeContext(getContext(), emailField, passwordField);
    }

    private void displayDialog(final String text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppTheme_Dialog)
                        .setMessage(text);
                builder.create().show();
            }
        });
    }
}
