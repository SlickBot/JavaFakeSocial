package com.slicky.ulj.javafakesocial.activity.content;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.slicky.ulj.javafakesocial.R;
import com.slicky.ulj.javafakesocial.activity.about.AboutActivity;
import com.slicky.ulj.javafakesocial.activity.friends.FriendsActivity;
import com.slicky.ulj.javafakesocial.activity.login.LoginActivity;
import com.slicky.ulj.javafakesocial.activity.profile.ProfileActivity;
import com.slicky.ulj.javafakesocial.activity.settings.SettingsActivity;
import com.slicky.ulj.javafakesocial.model.content.Content;
import com.slicky.ulj.javafakesocial.model.person.Person;
import com.slicky.ulj.javafakesocial.activity.ProgressDialogTask;

import java.util.List;

public class ContentActivity
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ContentAdapter contentAdapter;

    private ContentTask contentTask;
    private ProgressDialogTask userTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        RecyclerView recycler = (RecyclerView) findViewById(R.id.content_recycler_view);
        contentAdapter = new ContentAdapter(this, recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(contentAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        contentTask = new ContentTask(this);
        contentTask.execute();
    }

    @Override
    protected void onStop() {
        super.onStop();
        contentTask.cancel();
        if (userTask != null)
            userTask.cancel();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            startUserTask();
        } else if (id == R.id.nav_friends) {
            openFriends();
        } else if (id == R.id.nav_settings) {
            openSettings();
        } else if (id == R.id.nav_about) {
            openAbout();
        } else if (id == R.id.nav_share) {
            share();
        } else if (id == R.id.nav_logout) {
            logOut();
        }

        drawer.closeDrawers();
        return false;
    }

    private void startUserTask() {
        userTask = new UserTask(this);
        userTask.execute();
    }

    void openProfile(Person person, boolean isOwner) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("person", person);
        intent.putExtra("is_owner", isOwner);
        startActivity(intent);
    }

    private void openFriends() {
        Intent intent = new Intent(this, FriendsActivity.class);
        startActivity(intent);
    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void openAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Fakest Social Network!");
        intent.putExtra(Intent.EXTRA_TEXT, "This app is really FAKE!");
        startActivity(Intent.createChooser(intent, "Share via"));
    }

    private void logOut() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void setContent(List<Content> contents) {
        contentAdapter.setContent(contents);
    }

    void handleError(final String text, Exception e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(ContentActivity.this, R.style.AppTheme_Dialog)
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
}
