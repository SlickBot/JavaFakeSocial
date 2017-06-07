package com.slicky.ulj.javafakesocial.activity.friends;

import com.slicky.ulj.javafakesocial.activity.ProgressDialogTask;
import com.slicky.ulj.javafakesocial.model.person.Person;
import com.slicky.ulj.javafakesocial.db.FakeDBHandler;

import java.io.IOException;
import java.util.List;

/**
 * Created by SlickyPC on 24.5.2017
 */
class FriendsTask extends ProgressDialogTask<List<Person>> {

    private FriendsActivity activity;

    FriendsTask(FriendsActivity activity) {
        super(activity, "Loading Friends...");
        this.activity = activity;
    }

    @Override
    public List<Person> backgroundTask() throws IOException {
        return FakeDBHandler.getInstance().getFriends();
    }

    @Override
    public void success(List<Person> result) {
        activity.setFriends(result);
    }

    @Override
    public void fail(Exception e) {
        activity.onFail("Could not retrieve Friends!", e);
    }
}
