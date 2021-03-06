package com.ulj.slicky.javafakesocial.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ulj.slicky.javafakesocial.FakeUtils;
import com.ulj.slicky.javafakesocial.R;
import com.ulj.slicky.javafakesocial.activity.content.ContentActivity;

/**
 * Created by SlickyPC on 18.5.2017
 */
public class SignUpFragment extends Fragment {

    private static final String TAG = SignUpFragment.class.getCanonicalName();

    private EditText firstField;
    private EditText lastField;
    private EditText emailField;
    private EditText firstPasswordField;
    private EditText secondPasswordField;

    private SignUpTask task;

    static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_fragment, container, false);

        firstField = view.findViewById(R.id.signup_first_name);
        lastField = view.findViewById(R.id.signup_last_name);
        emailField = view.findViewById(R.id.signup_email);
        firstPasswordField = view.findViewById(R.id.signup_first_password);
        secondPasswordField = view.findViewById(R.id.signup_second_password);

        Button signUpButton = view.findViewById(R.id.signup_signup_button);
        signUpButton.setOnClickListener(v -> trySignup());

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (task != null)
            task.cancel();
    }

    private void trySignup() {
        View view = getView();
        if (view == null) {
            return;
        }

        SignUpValidator validator = new SignUpValidator(view);
        if (validator.validate()) {
            if (validator.acceptedLegalNotice()) {
                task = new SignUpTask(
                        this,
                        firstField.getText().toString(),
                        lastField.getText().toString(),
                        emailField.getText().toString(),
                        firstPasswordField.getText().toString()
                );
                task.execute();
            } else {
                displayDialog("You have to accept legal notice!");
            }
        } else {
            shakeStage();
        }
    }

    void successSignup() {
        Intent intent = new Intent(getActivity(), ContentActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }

    void failSignup(String text, Exception e) {
        displayDialog(text + (e != null ? "\n" + e.getLocalizedMessage() : ""));
        shakeStage();
        Log.wtf(TAG, text, e);
    }

    private void shakeStage() {
        FakeUtils.shakeContext(requireContext(), firstField, lastField, emailField, firstPasswordField, secondPasswordField);
    }

    private void displayDialog(final String text) {
        requireActivity().runOnUiThread(() -> new AlertDialog.Builder(requireContext(), R.style.AppTheme_Dialog)
                .setMessage(text)
                .create()
                .show());
    }

}
