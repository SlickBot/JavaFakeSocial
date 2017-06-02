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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.slicky.ulj.javafakesocial.R;
import com.slicky.ulj.javafakesocial.activity.ProgressDialogTask;
import com.slicky.ulj.javafakesocial.activity.about.AboutActivity;
import com.slicky.ulj.javafakesocial.activity.creator.CreatorActivity;
import com.slicky.ulj.javafakesocial.activity.friends.FriendsActivity;
import com.slicky.ulj.javafakesocial.activity.login.LoginActivity;
import com.slicky.ulj.javafakesocial.activity.profile.ProfileActivity;
import com.slicky.ulj.javafakesocial.activity.settings.SettingsActivity;
import com.slicky.ulj.javafakesocial.model.content.Content;
import com.slicky.ulj.javafakesocial.model.person.Person;
import com.slicky.ulj.javafakesocial.rest.DummyDBHandler;

import java.util.List;

public class ContentActivity
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = ContentActivity.class.getCanonicalName();

    private DrawerLayout drawer;
    private ContentAdapter contentAdapter;

    private ContentTask contentTask;
    private ProgressDialogTask userTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!DummyDBHandler.getInstance().isSignedIn())
            logOut();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.content_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_create) {
            openCreator();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startUserTask() {
        userTask = new UserTask(this);
        userTask.execute();
    }

    private void openCreator() {
        Intent intent = new Intent(this, CreatorActivity.class);
        startActivity(intent);
    }

    void openOwnerProfile(Person person) {
        Intent intent = ProfileActivity.getOwnerIntent(this, person);
        startActivity(intent);
    }

    void openFriendProfile(Person person) {
        Intent intent = ProfileActivity.getFriendIntent(this, person);
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

    public void setContent(List<Content> contents) {
        contentAdapter.setContent(contents);
    }

    private void logOut() {
        DummyDBHandler.getInstance().signout();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    void handleError(String text, Exception e) {
        displaySignOutDialog(text + (e != null ? "\n" + e.getLocalizedMessage() : ""));
        Log.wtf(TAG, text, e);
    }

    private void displaySignOutDialog(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(ContentActivity.this, R.style.AppTheme_Dialog)
                        .setMessage(text)
                        .setCancelable(false)
                        .setPositiveButton("Sign Out", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                logOut();
                            }
                        });
                builder.create().show();
            }
        });
    }
}
