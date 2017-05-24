package com.slicky.ulj.javafakesocial.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.slicky.ulj.javafakesocial.R;

import java.io.IOException;

/**
 * Created by SlickyPC on 21.5.2017
 */
public abstract class ProgressDialogTask<T> {

    private final ProgressDialog progress;
    private final AsyncTask<Void, Void, T> task;

    public ProgressDialogTask(Context context, String message) {
        progress = new ProgressDialog(context, R.style.AppTheme_Dialog);
        progress.setMessage(message);
        progress.setIndeterminate(true);
        progress.setCancelable(false);

        task = new AsyncTask<Void, Void, T>() {
            @Override
            protected void onPreExecute() {
                progress.show();
            }

            @Override
            protected T doInBackground(Void... voids) {
                try {
                    return backgroundTask();
                } catch (Exception e) {
                    if (!isCancelled()) {
                        fail(e);
                        cancel(true);
                    }
                    return null;
                }
            }

            @Override
            protected void onPostExecute(final T result) {
                progress.dismiss();
                success(result);
            }

            @Override
            protected void onCancelled() {
                progress.dismiss();
            }
        };
    }

    public void execute() {
        task.execute();
    }

    public void cancel() {
        progress.dismiss();
        task.cancel(true);
    }

    public abstract T backgroundTask() throws IOException;

    public abstract void success(T result);

    public abstract void fail(Exception e);
}
