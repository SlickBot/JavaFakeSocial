package com.ulj.slicky.javafakesocial.activity.content;

import com.ulj.slicky.javafakesocial.activity.ProgressDialogTask;
import com.ulj.slicky.javafakesocial.db.FakeDBHandler;
import com.ulj.slicky.javafakesocial.model.person.Person;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by SlickyPC on 23.5.2017
 */
class UserTask extends ProgressDialogTask<Person> {

    private final WeakReference<ContentActivity> activityReference;

    UserTask(ContentActivity activity) {
        super(activity, "Loading User...");
        this.activityReference = new WeakReference<>(activity);
    }

    @Override
    public Person backgroundTask() throws IOException {
        return FakeDBHandler.getInstance().getUser();
    }

    @Override
    public void success(Person user) {
        activityReference.get().openOwnerProfile(user);
    }

    @Override
    public void fail(Exception e) {
        activityReference.get().handleError("Could not load User data!", e);
    }
}
