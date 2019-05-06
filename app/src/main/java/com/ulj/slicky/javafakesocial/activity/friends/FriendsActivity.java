package com.ulj.slicky.javafakesocial.activity.friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ulj.slicky.javafakesocial.R;
import com.ulj.slicky.javafakesocial.activity.BackableActivity;
import com.ulj.slicky.javafakesocial.activity.login.LoginActivity;
import com.ulj.slicky.javafakesocial.activity.profile.ProfileActivity;
import com.ulj.slicky.javafakesocial.model.person.Person;
import com.ulj.slicky.javafakesocial.db.FakeDBHandler;

import java.util.List;

/**
 * Created by SlickyPC on 22.5.2017
 */
public class FriendsActivity extends BackableActivity {
    private static final String TAG = FriendsActivity.class.getCanonicalName();

    private FriendsAdapter friendsAdapter;
    private FriendsTask friendsTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_activity);

        RecyclerView recycler = findViewById(R.id.friends_recycler_view);
        friendsAdapter = new FriendsAdapter(this, recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(friendsAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        friendsTask = new FriendsTask(this);
        friendsTask.execute();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (friendsTask != null)
            friendsTask.cancel();
    }

    void openFriendProfile(Person friend) {
        Intent intent = ProfileActivity.getFriendIntent(this, friend);
        startActivity(intent);
    }

    void setFriends(List<Person> friends) {
        friendsAdapter.setFriends(friends);
    }

    @SuppressWarnings("SameParameterValue")
    void onFail(String text, Exception e) {
        displaySignOutDialog(text + (e != null ? "\n" + e.getLocalizedMessage() : ""));
        Log.wtf(TAG, text, e);
    }

    private void logOut() {
        FakeDBHandler.getInstance().signout();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void displaySignOutDialog(final String text) {
        runOnUiThread(() -> new AlertDialog.Builder(FriendsActivity.this, R.style.AppTheme_Dialog)
                .setMessage(text)
                .setCancelable(false)
                .setPositiveButton("Sign Out", (dialog, id) -> logOut())
                .create()
                .show());
    }

}
