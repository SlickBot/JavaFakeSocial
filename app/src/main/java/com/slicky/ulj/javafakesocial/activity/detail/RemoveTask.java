package com.slicky.ulj.javafakesocial.activity.detail;

import com.slicky.ulj.javafakesocial.activity.ProgressDialogTask;
import com.slicky.ulj.javafakesocial.db.FakeDBHandler;
import com.slicky.ulj.javafakesocial.model.content.Content;

import java.io.IOException;

/**
 * Created by SlickyPC on 8.6.2017
 */
class RemoveTask extends ProgressDialogTask<Void> {

    private final DetailActivity activity;
    private final Content content;

    RemoveTask(DetailActivity activity, Content content) {
        super(activity, "Removing Content...");
        this.activity = activity;
        this.content = content;
    }

    @Override
    public Void backgroundTask() throws IOException {
        return FakeDBHandler.getInstance().removeContent(content);
    }

    @Override
    public void success(Void result) {
        activity.finish();
    }

    @Override
    public void fail(Exception e) {
        activity.handleError("Error occurred while removing Content!", e);
    }
}
