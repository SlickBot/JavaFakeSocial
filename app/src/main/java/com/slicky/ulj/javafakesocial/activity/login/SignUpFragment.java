package com.slicky.ulj.javafakesocial.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.slicky.ulj.javafakesocial.R;

/**
 * Created by SlickyPC on 18.5.2017
 */
public class SignUpFragment extends Fragment {

    private EditText first;
    private EditText last;
    private EditText email;
    private EditText password;

    private SignUpTask task;

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
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        first = (EditText) view.findViewById(R.id.first_name);
        last = (EditText) view.findViewById(R.id.last_name);
        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);

        Button button = (Button) view.findViewById(R.id.sign_up);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignupClick();
            }
        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (task != null)
            task.cancel();
    }

    private void onSignupClick() {
        // TODO: validate fields
        task = new SignUpTask(this,
                first.getText().toString(),
                last.getText().toString(),
                email.getText().toString(),
                password.getText().toString());
        task.execute();
    }

    void handleError(final String text, Exception e) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(getContext(), R.style.AppTheme_Dialog)
                        .setMessage(text)
                        .create()
                        .show();
            }
        });
        // TODO: LOG IT!
        e.printStackTrace();
    }

    void failSignup() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(getContext(), R.style.AppTheme_Dialog)
                        .setMessage("Could not create new account!")
                        .create()
                        .show();
            }
        });
    }
}
