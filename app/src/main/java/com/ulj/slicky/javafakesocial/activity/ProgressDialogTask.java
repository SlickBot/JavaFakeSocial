package com.ulj.slicky.javafakesocial.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;

import com.ulj.slicky.javafakesocial.R;

import java.io.IOException;

/**
 * Created by SlickyPC on 21.5.2017
 */
public abstract class ProgressDialogTask<T> extends AsyncTask<Void, Void, T> {

    private final ProgressDialog progress;

    @SuppressLint("DEPRECATION")
    public ProgressDialogTask(Context context, String message) {
        Drawable logo = ContextCompat.getDrawable(context, R.drawable.loading_drawable);
        progress = new ProgressDialog(context, R.style.AppTheme_Dialog);
        progress.setMessage(message);
        progress.setIndeterminate(true);
        progress.setIndeterminateDrawable(logo);
        progress.setCancelable(false);
    }

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

    public void cancel() {
        progress.dismiss();
        cancel(true);
    }

    public abstract T backgroundTask() throws IOException;

    public abstract void success(T result);

    public abstract void fail(Exception e);

}
