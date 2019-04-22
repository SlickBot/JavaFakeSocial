package com.ulj.slicky.javafakesocial.activity.creator;

import com.ulj.slicky.javafakesocial.activity.ProgressDialogTask;
import com.ulj.slicky.javafakesocial.db.FakeDBHandler;

import java.lang.ref.WeakReference;

/**
 * Created by SlickyPC on 30.5.2017
 */
class CreatorTask extends ProgressDialogTask<Boolean> {

    private final WeakReference<CreatorActivity> activityReference;
    private final String content;

    CreatorTask(CreatorActivity activity, String content) {
        super(activity, "Uploading Content...");
        this.activityReference = new WeakReference<>(activity);
        this.content = content;
    }

    @Override
    public Boolean backgroundTask() {
        return FakeDBHandler.getInstance().uploadContent(content);
    }

    @Override
    public void success(Boolean result) {
        if (result)
            activityReference.get().onCreatingSuccess();
        else
            activityReference.get().onCreatingFail("Error occurred while uploading Content to Server!", null);
    }

    @Override
    public void fail(Exception e) {
        activityReference.get().onCreatingFail("Error occurred while uploading Content to Database!", e);
    }

}

