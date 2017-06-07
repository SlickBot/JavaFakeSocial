package com.slicky.ulj.javafakesocial.activity.content;

import com.slicky.ulj.javafakesocial.activity.ProgressDialogTask;
import com.slicky.ulj.javafakesocial.model.person.Person;
import com.slicky.ulj.javafakesocial.db.FakeDBHandler;

import java.io.IOException;

/**
 * Created by SlickyPC on 23.5.2017
 */
class UserTask extends ProgressDialogTask<Person> {

    private ContentActivity activity;

    UserTask(ContentActivity activity) {
        super(activity, "Loading User...");
        this.activity = activity;
    }

    @Override
    public Person backgroundTask() throws IOException {
        return FakeDBHandler.getInstance().getUser();
    }

    @Override
    public void success(Person user) {
        activity.openOwnerProfile(user);
    }

    @Override
    public void fail(Exception e) {
        activity.handleError("Could not load User data!", e);
    }
}
