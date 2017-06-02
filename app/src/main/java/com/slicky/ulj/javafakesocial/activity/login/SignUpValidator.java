package com.slicky.ulj.javafakesocial.activity.login;

import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import com.slicky.ulj.javafakesocial.R;

/**
 * Created by SlickyPC on 1.6.2017
 */
class SignUpValidator {

    private TextInputLayout firstLayout;
    private TextInputLayout lastLayout;
    private TextInputLayout emailLayout;
    private TextInputLayout firstPasswordLayout;
    private TextInputLayout secondPasswordLayout;

    private EditText firstField;
    private EditText lastField;
    private EditText emailField;
    private EditText firstPasswordField;
    private EditText secondPasswordField;

    private CheckBox legalCheckBox;

    SignUpValidator(View view) {
        firstLayout = (TextInputLayout) view.findViewById(R.id.signup_first_name_layout);
        lastLayout = (TextInputLayout) view.findViewById(R.id.signup_last_name_layout);
        emailLayout = (TextInputLayout) view.findViewById(R.id.signup_email_layout);
        firstPasswordLayout = (TextInputLayout) view.findViewById(R.id.signup_first_password_layout);
        secondPasswordLayout = (TextInputLayout) view.findViewById(R.id.signup_second_password_layout);

        firstField = firstLayout.getEditText();
        lastField = lastLayout.getEditText();
        emailField = emailLayout.getEditText();
        firstPasswordField = firstPasswordLayout.getEditText();
        secondPasswordField = secondPasswordLayout.getEditText();

        legalCheckBox = (CheckBox) view.findViewById(R.id.legal_checkbox);
    }

    boolean validate() {
        View errorField = null;

        firstLayout.setError(null);
        lastLayout.setError(null);
        emailLayout.setError(null);
        firstPasswordLayout.setError(null);
        secondPasswordLayout.setError(null);

        String first = firstField.getText().toString();
        String last = lastField.getText().toString();
        String email = emailField.getText().toString();
        String firstPassword = firstPasswordField.getText().toString();
        String secondPassword = secondPasswordField.getText().toString();

        if (first.length() < 2) {
            firstLayout.setError("First name is too short! (min 2)");
            errorField = firstLayout;
        } else if (first.length() > 50) {
            firstLayout.setError("First name is too long! (max 50)");
            errorField = firstLayout;
        }

        if (last.length() < 2) {
            lastLayout.setError("Last name is too short! (min 2)");
            if (errorField == null)
                errorField = lastLayout;
        } else if (last.length() > 50) {
            lastLayout.setError("Last name is too long! (max 50)");
            if (errorField == null)
                errorField = lastLayout;
        }

        if (email.length() < 5) {
            emailLayout.setError("Email is too short! (min 5)");
            errorField = emailLayout;
        } else if (email.length() > 50) {
            emailLayout.setError("Email is too long! (max 50)");
            if (errorField == null)
                errorField = emailLayout;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Email is not valid!");
            if (errorField == null)
                errorField = emailLayout;
        }

        if (firstPassword.length() < 5) {
            firstPasswordLayout.setError("Password is too short! (min 5)");
            if (errorField == null)
                errorField = firstPasswordLayout;
        } else if (firstPassword.length() > 50) {
            firstPasswordLayout.setError("Password is too long! (max 50)");
            if (errorField == null)
                errorField = firstPasswordLayout;
        } else if (!secondPassword.equals(firstPassword)) {
            secondPasswordLayout.setError("Passwords do not match!");
            if (errorField == null)
                errorField = secondPasswordLayout;
        }

        if (errorField != null) {
            errorField.requestFocus();
            return false;
        }

        return true;
    }

    boolean acceptedLegalNotice() {
        return legalCheckBox.isChecked();
    }
}
