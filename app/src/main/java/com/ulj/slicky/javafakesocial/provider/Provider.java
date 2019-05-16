package com.ulj.slicky.javafakesocial.provider;

import com.ulj.slicky.javafakesocial.model.person.Person;
import com.ulj.slicky.javafakesocial.model.person.PersonDOB;
import com.ulj.slicky.javafakesocial.model.person.PersonID;
import com.ulj.slicky.javafakesocial.model.person.PersonLocation;
import com.ulj.slicky.javafakesocial.model.person.PersonLogin;
import com.ulj.slicky.javafakesocial.model.person.PersonName;
import com.ulj.slicky.javafakesocial.model.person.PersonPicture;
import com.ulj.slicky.javafakesocial.model.person.PersonRegistered;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Provider {

    private static Provider instance = null;

    public static Provider getInstance() {
        if (instance == null) {
            instance = new Provider();
        }
        return instance;
    }

    public String getContent() {
        return "this is test content";
    }

    public List<Person> getPersons(int amount) {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            persons.add(getPerson(i));
        }
        return persons;
    }

    private Person getPerson(int id) {
        return new Person(
                "gender",
                new PersonName(
                        "title",
                        "first",
                        "last"
                ),
                new PersonLocation(
                        "street",
                        "city",
                        "state",
                        "postcode"
                ),
                "email",
                new PersonLogin(
                        "username",
                        "password",
                        "salt",
                        "md5",
                        "sha1",
                        "sha256"
                ),
                new PersonDOB(
                        new Date(),
                        21
                ),
                new PersonRegistered(
                        new Date(),
                        21
                ),
                "phone",
                "cell",
                new PersonID(
                        "name",
                        String.valueOf(id)
                ),
                new PersonPicture(
                        "https://randomuser.me/api/portraits/men/64.jpg",
                        "https://randomuser.me/api/portraits/men/64.jpg",
                        "https://randomuser.me/api/portraits/men/64.jpg"
                ),
                "nat"
        );
    }

}
