package com.ulj.slicky.javafakesocial.activity.friends;

import com.ulj.slicky.javafakesocial.activity.ProgressDialogTask;
import com.ulj.slicky.javafakesocial.db.FakeDBHandler;
import com.ulj.slicky.javafakesocial.model.person.Person;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by SlickyPC on 24.5.2017
 */
class FriendsTask extends ProgressDialogTask<List<Person>> {

    private final WeakReference<FriendsActivity> activityReference;

    FriendsTask(FriendsActivity activity) {
        super(activity, "Loading Friends...");
        this.activityReference = new WeakReference<>(activity);
    }

    @Override
    public List<Person> backgroundTask() throws IOException {
        return FakeDBHandler.getInstance().getFriends();
    }

    @Override
    public void success(List<Person> result) {
        activityReference.get().setFriends(result);
    }

    @Override
    public void fail(Exception e) {
        activityReference.get().onFail("Could not retrieve Friends!", e);
    }

}
