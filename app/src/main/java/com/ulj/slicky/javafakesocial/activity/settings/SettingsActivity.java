package com.ulj.slicky.javafakesocial.activity.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.ulj.slicky.javafakesocial.FakePreferences;
import com.ulj.slicky.javafakesocial.R;
import com.ulj.slicky.javafakesocial.activity.BackableActivity;
import com.ulj.slicky.javafakesocial.activity.service.NotifyingService;

import java.util.Locale;

/**
 * Created by SlickyPC on 22.5.2017
 */
public class SettingsActivity extends BackableActivity {

    private FakePreferences prefs;

    private Switch onOffSwitch;
    private EditText durationField;
    private TextView durationText;
    private Switch randomSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        prefs = new FakePreferences(this);

        onOffSwitch = findViewById(R.id.settings_on_off_switch);
        durationField = findViewById(R.id.settings_duration_field);
        durationText = findViewById(R.id.settings_duration_text);
        randomSwitch = findViewById(R.id.settings_random_switch);

        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefs.setNotifyOn(isChecked);
                updateFields();
                if (isChecked)
                    startNotifyService();
            }
        });

        durationField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    int duration = Integer.parseInt(durationField.getText().toString());
                    prefs.setNotifyDuration(duration);
                    updateFields();
                }
                return false;
            }
        });

        randomSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefs.setNotifyRandom(isChecked);
                updateFields();
            }
        });


        updateFields();
    }

    private void startNotifyService() {
        Intent intent = new Intent(SettingsActivity.this, NotifyingService.class);
        startService(intent);
    }

    private void updateFields() {
        boolean onOff = prefs.isNotifyOn();
        int duration = prefs.getNotifyDuration();
        boolean random = prefs.isNotifyRandom();

        onOffSwitch.setChecked(onOff);
        durationField.setText(String.format(Locale.getDefault(), "%d", duration));
        randomSwitch.setChecked(random);

        int colorId = random ? R.color.colorTextDark : R.color.colorText;
        int color = ContextCompat.getColor(this, colorId);

        durationText.setTextColor(color);
        durationField.setTextColor(color);
        durationField.setEnabled(!random);
    }

}
