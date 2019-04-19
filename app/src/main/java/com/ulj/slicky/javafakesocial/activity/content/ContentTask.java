package com.ulj.slicky.javafakesocial.activity.content;

import com.ulj.slicky.javafakesocial.activity.ProgressDialogTask;
import com.ulj.slicky.javafakesocial.db.FakeDBHandler;
import com.ulj.slicky.javafakesocial.model.content.Content;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by SlickyPC on 21.5.2017
 */
class ContentTask extends ProgressDialogTask<List<Content>> {

    private final WeakReference<ContentActivity> activityReference;

    ContentTask(ContentActivity activity) {
        super(activity, "Loading Content...");
        this.activityReference = new WeakReference<>(activity);
    }

    @Override
    public List<Content> backgroundTask() throws IOException {
        return FakeDBHandler.getInstance().getContent();
    }

    @Override
    public void success(List<Content> result) {
        activityReference.get().setContent(result);
    }

    @Override
    public void fail(Exception e) {
        activityReference.get().handleError("Could not retrieve Content!", e);
    }

}
