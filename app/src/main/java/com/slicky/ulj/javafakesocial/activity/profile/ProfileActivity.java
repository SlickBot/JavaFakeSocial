package com.slicky.ulj.javafakesocial.activity.profile;

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

import static org.apache.commons.lang3.text.WordUtils.capitalize;

/**
 * Created by SlickyPC on 22.5.2017
 */
public class ProfileActivity extends BackableActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Person person = extras.getParcelable("person");
        boolean isOwner = extras.getBoolean("is_owner");

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
        CharSequence birthday = FakeUtils.getFullDate(person.getDob());
        CharSequence registered = FakeUtils.getFullDate(person.getRegistered());
        String street = capitalize(person.getLocation().getStreet());
        String city = capitalize(person.getLocation().getCity());
        String state = capitalize(person.getLocation().getState());
        String nat = FakeUtils.getCountry(person.getNat());

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
