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
public class SignInFragment extends Fragment {

    private EditText email;
    private EditText password;

    private SignInTask task;

    static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);

        Button button = (Button) view.findViewById(R.id.sign_in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClick();
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

    private void onLoginClick() {
        // TODO: validate fields
        task = new SignInTask(this,
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

    void failLogin() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(getContext(), R.style.AppTheme_Dialog)
                        .setMessage("Invalid login data!")
                        .create()
                        .show();
            }
        });
    }
}
