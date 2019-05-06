package com.ulj.slicky.javafakesocial.activity.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulj.slicky.javafakesocial.FakeUtils;
import com.ulj.slicky.javafakesocial.R;
import com.ulj.slicky.javafakesocial.activity.BackableActivity;
import com.ulj.slicky.javafakesocial.model.person.Person;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by SlickyPC on 22.5.2017
 */
public class ProfileActivity extends BackableActivity {

    private static final String TAG = ProfileActivity.class.getCanonicalName();
    private static final String KEY_PERSON = TAG + ".person";
    private static final String KEY_OWNER = TAG + ".owner";

    public static Intent getOwnerIntent(Context context, Person owner) {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra(KEY_PERSON, owner);
        intent.putExtra(KEY_OWNER, true);
        return intent;
    }

    public static Intent getFriendIntent(Context context, Person friend) {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra(KEY_PERSON, friend);
        intent.putExtra(KEY_OWNER, false);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }

        Person person = extras.getParcelable(KEY_PERSON);
        if (person == null) {
            return;
        }

        boolean isOwner = extras.getBoolean(KEY_OWNER);

        if (isOwner)
            setTitle("Your Profile");
        else
            setTitle(FakeUtils.getFullPersonName(person));

        ImageView imageView = findViewById(R.id.profile_icon);
        TextView nameField = findViewById(R.id.profile_name);
        TextView emailField = findViewById(R.id.profile_email);
        TextView cellField = findViewById(R.id.profile_cell);
        TextView phoneField = findViewById(R.id.profile_phone);
        TextView birthdayField = findViewById(R.id.profile_birthday);
        TextView registeredField = findViewById(R.id.profile_registered);
        TextView streetField = findViewById(R.id.profile_street);
        TextView cityField = findViewById(R.id.profile_city);
        TextView stateField = findViewById(R.id.profile_state);
        TextView natField = findViewById(R.id.profile_nat);

        String imageUrl = person.getPicture().getLarge();
        String name = FakeUtils.getFullPersonNameWithTitle(person);
        String email = person.getEmail();
        String cell = person.getCell();
        String phone = person.getPhone();
        CharSequence birthday = FakeUtils.getFormattedWithTime(person.getDob().getDate());
        CharSequence registered = FakeUtils.getFormattedWithTime(person.getRegistered().getDate());
        String street = FakeUtils.capitalizeAll(person.getLocation().getStreet());
        String city = FakeUtils.capitalizeAll(person.getLocation().getCity());
        String state = FakeUtils.capitalizeAll(person.getLocation().getState());
        String nat = FakeUtils.getCountryFromCode(person.getNat());

        if (FakeUtils.isAppiumTest()) {
            Picasso.with(this).load(R.drawable.test_img)
                    .placeholder(R.drawable.ic_user)
                    .transform(new CropCircleTransformation())
                    .into(imageView);
        } else {
            Picasso.with(this).load(imageUrl)
                    .placeholder(R.drawable.ic_user)
                    .transform(new CropCircleTransformation())
                    .into(imageView);
        }

        nameField.setText(name);
        emailField.setText(email);
        cellField.setText(cell);
        phoneField.setText(phone);
        birthdayField.setText(birthday);
        registeredField.setText(registered);
        streetField.setText(street);
        cityField.setText(city);
        stateField.setText(state);
        natField.setText(nat);
    }

}
