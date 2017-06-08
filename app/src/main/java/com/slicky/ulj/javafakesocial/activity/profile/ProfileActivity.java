package com.slicky.ulj.javafakesocial.activity.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import com.slicky.ulj.javafakesocial.FakeUtils;
import com.slicky.ulj.javafakesocial.R;
import com.slicky.ulj.javafakesocial.activity.BackableActivity;
import com.slicky.ulj.javafakesocial.model.person.Person;
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
        Person person = extras.getParcelable(KEY_PERSON);
        boolean isOwner = extras.getBoolean(KEY_OWNER);

        if (isOwner)
            setTitle("Your Profile");
        else
            setTitle(FakeUtils.getFullPersonName(person));

        ImageView imageView = (ImageView) findViewById(R.id.profile_icon);
        TextView nameField = (TextView) findViewById(R.id.profile_name);
        TextView emailField = (TextView) findViewById(R.id.profile_email);
        TextView cellField = (TextView) findViewById(R.id.profile_cell);
        TextView phoneField = (TextView) findViewById(R.id.profile_phone);
        TextView birthdayField = (TextView) findViewById(R.id.profile_birthday);
        TextView registeredField = (TextView) findViewById(R.id.profile_registered);
        TextView streetField = (TextView) findViewById(R.id.profile_street);
        TextView cityField = (TextView) findViewById(R.id.profile_city);
        TextView stateField = (TextView) findViewById(R.id.profile_state);
        TextView natField = (TextView) findViewById(R.id.profile_nat);

        String imageUrl = person.getPicture().getLarge();
        String name = FakeUtils.getFullPersonNameWithTitle(person);
        String email = person.getEmail();
        String cell = person.getCell();
        String phone = person.getPhone();
        CharSequence birthday = FakeUtils.getFormattedWithTime(person.getDob());
        CharSequence registered = FakeUtils.getFormattedWithTime(person.getRegistered());
        String street = FakeUtils.capitalizeAll(person.getLocation().getStreet());
        String city = FakeUtils.capitalizeAll(person.getLocation().getCity());
        String state = FakeUtils.capitalizeAll(person.getLocation().getState());
        String nat = FakeUtils.getCountryFromCode(person.getNat());

        Picasso.with(this).load(imageUrl)
                .placeholder(R.drawable.ic_user)
                .transform(new CropCircleTransformation())
                .into(imageView);

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
