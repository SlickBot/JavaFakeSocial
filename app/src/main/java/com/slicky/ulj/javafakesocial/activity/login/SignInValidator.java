package com.slicky.ulj.javafakesocial.activity.login;

import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import com.slicky.ulj.javafakesocial.R;

/**
 * Created by SlickyPC on 1.6.2017
 */
class SignInValidator {

    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;

    private EditText emailField;
    private EditText passwordField;

    SignInValidator(View view) {
        emailLayout = (TextInputLayout) view.findViewById(R.id.signin_email_layout);
        emailField = emailLayout.getEditText();

        passwordLayout = (TextInputLayout) view.findViewById(R.id.signin_password_layout);
        passwordField = passwordLayout.getEditText();
    }

    boolean validate() {
        emailLayout.setError(null);
        passwordLayout.setError(null);

        View errorField = null;

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (email.length() < 5) {
            emailLayout.setError("Email is too short! (min 5)");
            errorField = emailLayout;
        } else if (email.length() > 50) {
            emailLayout.setError("Email is too long! (max 50)");
            errorField = emailLayout;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Email is not valid!");
            if (errorField == null)
                errorField = emailLayout;
        }

        if (password.length() < 5) {
            passwordLayout.setError("Password is too short! (min 5)");
            if (errorField == null)
                errorField = passwordLayout;
        } else if (password.length() > 50) {
            passwordLayout.setError("Password is too long! (max 50)");
            if (errorField == null)
                errorField = passwordLayout;
        }

        if (errorField != null) {
            errorField.requestFocus();
            return false;
        }

        return true;
    }
}
