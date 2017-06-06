package com.slicky.ulj.javafakesocial.activity.creator;

import com.slicky.ulj.javafakesocial.activity.ProgressDialogTask;
import com.slicky.ulj.javafakesocial.db.DummyDBHandler;

import java.io.IOException;

/**
 * Created by SlickyPC on 30.5.2017
 */
class CreatorTask extends ProgressDialogTask<Boolean> {

    private final CreatorActivity activity;
    private final String content;

    CreatorTask(CreatorActivity activity, String content) {
        super(activity, "Uploading Content...");
        this.activity = activity;
        this.content = content;
    }

    @Override
    public Boolean backgroundTask() throws IOException {
        return DummyDBHandler.getInstance().uploadContent(content);
    }

    @Override
    public void success(Boolean result) {
        if (result)
            activity.onCreatingSuccess();
        else
            activity.onCreatingFail("Error occurred while uploading Content to Server!", null);
    }

    @Override
    public void fail(Exception e) {
        activity.onCreatingFail("Error occurred while uploading Content to Database!", e);
    }
}

