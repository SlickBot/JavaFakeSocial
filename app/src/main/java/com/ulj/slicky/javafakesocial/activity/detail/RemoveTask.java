package com.ulj.slicky.javafakesocial.activity.detail;

import com.ulj.slicky.javafakesocial.activity.ProgressDialogTask;
import com.ulj.slicky.javafakesocial.db.FakeDBHandler;
import com.ulj.slicky.javafakesocial.model.content.Content;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by SlickyPC on 8.6.2017
 */
class RemoveTask extends ProgressDialogTask<Void> {

    private final WeakReference<DetailActivity> activityReference;
    private final Content content;

    RemoveTask(DetailActivity activity, Content content) {
        super(activity, "Removing Content...");
        this.activityReference = new WeakReference<>(activity);
        this.content = content;
    }

    @Override
    public Void backgroundTask() throws IOException {
        return FakeDBHandler.getInstance().removeContent(content);
    }

    @Override
    public void success(Void result) {
        activityReference.get().finish();
    }

    @Override
    public void fail(Exception e) {
        activityReference.get().handleError("Error occurred while removing Content!", e);
    }
}
