package com.slicky.ulj.javafakesocial.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.slicky.ulj.javafakesocial.R;
import com.slicky.ulj.javafakesocial.activity.content.ContentActivity;

/**
 * Created by SlickyPC on 18.5.2017
 */
public class SignUpFragment extends Fragment {
    private static final String TAG = SignUpFragment.class.getCanonicalName();

    private EditText firstField;
    private EditText lastField;
    private EditText emailField;
    private EditText firstPasswordField;

    private SignUpTask task;
    private SignUpValidator validator;

    static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_fragment, container, false);

        validator = new SignUpValidator(view);

        firstField = (EditText) view.findViewById(R.id.signup_first_name);
        lastField = (EditText) view.findViewById(R.id.signup_last_name);
        emailField = (EditText) view.findViewById(R.id.signup_email);
        firstPasswordField = (EditText) view.findViewById(R.id.signup_first_password);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (task != null)
            task.cancel();
    }

    void trySignup() {
        if (validator.validate()) {
            if (validator.acceptedLegalNotice()) {
                task = new SignUpTask(this,
                        firstField.getText().toString(),
                        lastField.getText().toString(),
                        emailField.getText().toString(),
                        firstPasswordField.getText().toString());
                task.execute();
            } else {
                displayDialog("You have to accept legal notice!");
            }
        }
    }

    void successSignup() {
        Intent intent = new Intent(getActivity(), ContentActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    void failSignup(String text, Exception e) {
        displayDialog(text + (e != null ? "\n" + e.getLocalizedMessage() : ""));
        Log.wtf(TAG, text, e);
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
