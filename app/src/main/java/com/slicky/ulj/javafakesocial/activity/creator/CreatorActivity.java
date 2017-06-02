package com.slicky.ulj.javafakesocial.activity.creator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.slicky.ulj.javafakesocial.R;
import com.slicky.ulj.javafakesocial.activity.BackableActivity;

/**
 * Created by SlickyPC on 30.5.2017
 */
public class CreatorActivity extends BackableActivity {
    private static final String TAG = CreatorActivity.class.getCanonicalName();

    private EditText textField;
    private CreatorTask task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creator_activity);

        textField = (EditText) findViewById(R.id.creator_text);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (task != null)
            task.cancel();
    }

    public void onNewContent(View view) {
        if (validate()) {
            task = new CreatorTask(this, textField.getText().toString());
            task.execute();
        }
    }

    private boolean validate() {
        textField.setError(null);

        String text = textField.getText().toString();
        if (text.length() < 5) {
            textField.setError("Text is too short! (minimum is 5)");
            textField.requestFocus();
            return false;
        }
        if (text.length() > 256) {
            textField.setError("Text is too long! (minimum is 256)");
            textField.requestFocus();
            return false;
        }
        return true;
    }

    void onCreatingSuccess() {
        displayDialog("Successfully uploaded new Content!");
    }

    void onCreatingFail(String text, Exception e) {
        displayDialog(text + (e != null ? "\n" + e.getLocalizedMessage() : ""));
        Log.wtf(TAG, text, e);
    }

    private void displayDialog(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreatorActivity.this, R.style.AppTheme_Dialog)
                        .setMessage(text)
                        .setCancelable(false)
                        .setPositiveButton("Cool!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                builder.create().show();
            }
        });
    }
}
