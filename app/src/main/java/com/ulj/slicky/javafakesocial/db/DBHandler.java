package com.ulj.slicky.javafakesocial.db;

import com.ulj.slicky.javafakesocial.model.content.Content;
import com.ulj.slicky.javafakesocial.model.person.Person;

import java.io.IOException;
import java.util.List;

/**
 * Created by SlickyPC on 20.5.2017
 */
public interface DBHandler {

    boolean signin(String email, String password) throws IOException;

    boolean signup(String firstName,
                   String lastName,
                   String email,
                   String password) throws IOException;

    void signout();

    Boolean uploadContent(String content) throws IOException;

    Person getUser() throws IOException;

    List<Person> getFriends() throws IOException;

    List<Content> getContent() throws IOException;

    Void removeContent(Content content) throws IOException;
}
