package com.slicky.ulj.javafakesocial.rest;

import com.slicky.ulj.javafakesocial.model.content.Content;
import com.slicky.ulj.javafakesocial.model.person.Person;
import com.slicky.ulj.javafakesocial.model.person.PersonQuery;
import com.slicky.ulj.javafakesocial.rest.content.ContentService;
import com.slicky.ulj.javafakesocial.rest.person.PersonService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by SlickyPC on 20.5.2017
 */
public class DummyDBHandler implements DBHandler {

    private static DummyDBHandler instance = new DummyDBHandler();
    private static final Object lock = new Object();

    private Person user = null;
    private List<Person> friends = null;
    private List<Content> contents = null;

    private DummyDBHandler() {}

    public static DummyDBHandler getInstance() {
        return instance;
    }

    private void queryData() throws IOException {
        synchronized (lock) {
            List<Person> candidates = findCandidates();
            user = candidates.get(0);
            friends = candidates.subList(1, candidates.size());
            contents = generateContent();
        }
    }

    private List<Person> findCandidates() throws IOException {
        PersonQuery query = PersonService.getInstance()
                .getPerson(50, null, null, null, null)
                .execute().body();
        if (query == null)
            throw new IOException("Did not receive PersonQuery (is null)");
        List<Person> candidates = distinctByURL(query.getResults());
        if (candidates.size() < 1)
            throw new IOException("Did not receive enough candidates (size < 1)");
        return candidates;
    }

    private List<Content> generateContent() throws IOException {
        ArrayList<Content> list = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            String query = ContentService.getInstance()
                    .getContent("", "")
                    .execute().body();
            if (query == null)
                throw new IOException("Did not receive Content (is null)");
            Person randy = friends.get(new Random().nextInt(friends.size()));
            if (list.size() > 0) {
                Content last = list.get(list.size() - 1);
                int rand = rnd.nextInt(1000 * 60 * 60 * 24);
                list.add(new Content(randy, query, last.getPostedAt() - rand));
            } else {
                list.add(new Content(randy, query, System.currentTimeMillis()));
            }
        }
        return list;
    }

    private List<Person> distinctByURL(List<Person> people) {
        ArrayList<Person> filtered = new ArrayList<>();
        HashSet<String> urls = new HashSet<>();
        for (Person friend : people) {
            String url = friend.getPicture().getLarge();
            if (urls.add(url))
                filtered.add(friend);
        }
        return filtered;
    }

    @Override
    public boolean login(String username, String password) throws IOException {
        // Simulate work.
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignored) {}
        // Return user login matches dummy login.
//        return username.equals("user") && password.equals("pass");
        return true;
    }

    @Override
    public boolean signup(String firstName,
                          String lastName,
                          String email,
                          String password) throws IOException {
        // Simulate work.
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignored) {}
        // Anyone is welcomed.
        return true;
    }

    @Override
    public Person getUser() throws IOException {
        if (user == null)
            queryData();
        return user;
    }

    @Override
    public List<Person> getFriends() throws IOException {
        if (friends == null)
            queryData();
        return friends;
    }

    @Override
    public List<Content> getContent() throws IOException {
        if (contents == null)
            queryData();
        return contents;
    }
}
