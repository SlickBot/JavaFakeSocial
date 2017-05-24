package com.slicky.ulj.javafakesocial.activity.content;

import com.slicky.ulj.javafakesocial.activity.ProgressDialogTask;
import com.slicky.ulj.javafakesocial.model.content.Content;
import com.slicky.ulj.javafakesocial.rest.DummyDBHandler;

import java.io.IOException;
import java.util.List;

/**
 * Created by SlickyPC on 21.5.2017
 */
class ContentTask extends ProgressDialogTask<List<Content>> {

    private final ContentActivity activity;

    ContentTask(ContentActivity activity) {
        super(activity, "Loading content...");
        this.activity = activity;
    }

    @Override
    public List<Content> backgroundTask() throws IOException {
        return DummyDBHandler.getInstance().getContent();
    }

    @Override
    public void success(List<Content> result) {
        activity.setContent(result);
    }

    @Override
    public void fail(Exception e) {
        activity.handleError("Could not retrieve content!", e);
    }
}
