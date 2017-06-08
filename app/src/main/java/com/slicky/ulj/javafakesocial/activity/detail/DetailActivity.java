package com.slicky.ulj.javafakesocial.activity.detail;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.slicky.ulj.javafakesocial.FakeUtils;
import com.slicky.ulj.javafakesocial.R;
import com.slicky.ulj.javafakesocial.activity.BackableActivity;
import com.slicky.ulj.javafakesocial.activity.profile.ProfileActivity;
import com.slicky.ulj.javafakesocial.model.content.Content;
import com.squareup.picasso.Picasso;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import java.util.Date;

/**
 * Created by SlickyPC on 8.6.2017
 */
public class DetailActivity extends BackableActivity {
    private static final String TAG = ProfileActivity.class.getCanonicalName();
    private static final String KEY_CONTENT = TAG + ".content";

    private Content content;
    private RemoveTask task;

    public static Intent getDetailIntent(Context context, Content content) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_CONTENT, content);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        content = extras.getParcelable(KEY_CONTENT);

        ImageView imageView = (ImageView) findViewById(R.id.detail_image);
        TextView nameField = (TextView) findViewById(R.id.detail_owner_name);
        TextView postedAtField = (TextView) findViewById(R.id.detail_posted_at);
        TextView textField = (TextView) findViewById(R.id.detail_text);

        String imageUrl = content.getOwner().getPicture().getLarge();
        String name = FakeUtils.getFullPersonName(content.getOwner());
        CharSequence postedAtDate = FakeUtils.getFormattedWithTime(new Date(content.getPostedAt()));

        Picasso.with(this).load(imageUrl)
                .placeholder(R.drawable.ic_user)
                .transform(new CropCircleTransformation())
                .into(imageView);

        nameField.setText(name);
        postedAtField.setText("Posted at: " + postedAtDate);
        textField.setText(content.getText());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (task != null)
            task.cancel();
    }

    public void onOpenProfile(View view) {
        Intent intent = ProfileActivity.getFriendIntent(this, content.getOwner());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_remove) {
            displayConfirmationDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayConfirmationDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this, R.style.AppTheme_Dialog)
                        .setMessage("Do you really want to remove this Content?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startRemoveTask();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });
    }

    private void startRemoveTask() {
        task = new RemoveTask(this, content);
        task.execute();
    }

    void handleError(String text, Exception e) {
        displayDialog(text + (e != null ? "\n" + e.getLocalizedMessage() : ""));
        Log.wtf(TAG, text, e);
    }

    private void displayDialog(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this, R.style.AppTheme_Dialog)
                        .setMessage(text);
                builder.create().show();
            }
        });
    }
}