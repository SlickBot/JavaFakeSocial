package com.slicky.ulj.javafakesocial.activity.friends;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.slicky.ulj.javafakesocial.R;
import com.slicky.ulj.javafakesocial.activity.BackableActivity;
import com.slicky.ulj.javafakesocial.activity.login.LoginActivity;
import com.slicky.ulj.javafakesocial.activity.profile.ProfileActivity;
import com.slicky.ulj.javafakesocial.model.person.Person;

import java.util.List;

/**
 * Created by SlickyPC on 22.5.2017
 */
public class FriendsActivity extends BackableActivity {

    private FriendsAdapter friendsAdapter;
    private FriendsTask friendsTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.friends_recycler_view);
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

    void openProfile(Person friend) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("person", friend);
        intent.putExtra("is_owner", false);
        startActivity(intent);
    }

    public void setFriends(List<Person> friends) {
        friendsAdapter.setFriends(friends);
    }

    void handleError(final String text, Exception e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(FriendsActivity.this, R.style.AppTheme_Dialog)
                        .setMessage(text)
                        .setCancelable(false)
                        .setPositiveButton("Sign Out", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                logOut();
                            }
                        })
                        .create()
                        .show();
            }
        });
        // TODO: LOG IT!
        e.printStackTrace();
    }

    private void logOut() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
