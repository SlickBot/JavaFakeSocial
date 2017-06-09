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
        CreatorValidator validator = new CreatorValidator(this);
        if (validator.validate()) {
            task = new CreatorTask(this, textField.getText().toString());
            task.execute();
        }
    }

    void onCreatingSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreatorActivity.this, R.style.AppTheme_Dialog)
                        .setMessage("Successfully uploaded new Content!")
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

    void onCreatingFail(final String text, final Exception e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreatorActivity.this, R.style.AppTheme_Dialog)
                        .setMessage(text + (e != null ? "\n" + e.getLocalizedMessage() : ""));
                builder.create().show();
            }
        });
        Log.wtf(TAG, text, e);
    }
}
