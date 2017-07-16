package com.ulj.slicky.javafakesocial.activity.content;

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

import com.ulj.slicky.javafakesocial.FakeUtils;
import com.ulj.slicky.javafakesocial.R;
import com.ulj.slicky.javafakesocial.activity.about.AboutActivity;
import com.ulj.slicky.javafakesocial.activity.creator.CreatorActivity;
import com.ulj.slicky.javafakesocial.activity.detail.DetailActivity;
import com.ulj.slicky.javafakesocial.activity.friends.FriendsActivity;
import com.ulj.slicky.javafakesocial.activity.login.LoginActivity;
import com.ulj.slicky.javafakesocial.activity.profile.ProfileActivity;
import com.ulj.slicky.javafakesocial.activity.settings.SettingsActivity;
import com.ulj.slicky.javafakesocial.db.FakeDBHandler;
import com.ulj.slicky.javafakesocial.model.content.Content;
import com.ulj.slicky.javafakesocial.model.person.Person;

import java.util.List;

public class ContentActivity
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = ContentActivity.class.getCanonicalName();

    private DrawerLayout drawer;
    private ContentAdapter contentAdapter;

    private ContentTask contentTask;
    private UserTask userTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!FakeDBHandler.getInstance().isSignedIn())
            signOut();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity);

        // Thanks Lollipop -> https://stackoverflow.com/a/29455956/6814029
        setTitle("Content");

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.content_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        RecyclerView recycler = findViewById(R.id.content_recycler_view);
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
        if (contentTask != null)
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
        Intent intent;

        switch (item.getItemId()) {
            case R.id.nav_profile:
                userTask = new UserTask(this);
                userTask.execute();
                break;
            case R.id.nav_friends:
                intent = new Intent(this, FriendsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_share:
                FakeUtils.startShareActivity(this,
                        "Fakest Social Network!",
                        "This app is really FAKE!"
                );
                break;
            case R.id.nav_logout:
                signOut();
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
            Intent intent = new Intent(this, CreatorActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void openOwnerProfile(Person person) {
        Intent intent = ProfileActivity.getOwnerIntent(this, person);
        startActivity(intent);
    }

    void openDetails(Content content) {
        Intent intent = DetailActivity.getDetailIntent(this, content);
        startActivity(intent);
    }

    public void setContent(List<Content> contents) {
        contentAdapter.setContent(contents);
    }

    private void signOut() {
        FakeDBHandler.getInstance().signout();
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
                                signOut();
                            }
                        });
                builder.create().show();
            }
        });
    }
}
