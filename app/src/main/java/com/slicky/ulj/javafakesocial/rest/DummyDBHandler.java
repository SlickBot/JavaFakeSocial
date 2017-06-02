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

    private static final DummyDBHandler instance = new DummyDBHandler();
    private static final Object lock = new Object();

    private Person user = null;
    private List<Person> friends = null;
    private List<Content> contents = null;
    private boolean signedIn;

    private DummyDBHandler() {}

    public static DummyDBHandler getInstance() {
        return instance;
    }

    private void queryData() throws IOException {
        synchronized (lock) {
            // Find person candidates.
            List<Person> candidates = findCandidates();
            // Use first person as user.
            user = candidates.get(0);
            // Use rest as friends.
            friends = candidates.subList(1, candidates.size());
            // Generate content.
            contents = generateContent();
        }
    }

    private List<Person> findCandidates() throws IOException {
        // Query PersonQuery for new random persons.
        PersonQuery query = PersonService.getInstance()
                .getPerson(50, null, null, null, null)
                .execute().body();

        if (query == null)
            throw new IOException("Did not receive PersonQuery (is null)");

        // Distinct received persons by image URL.
        List<Person> candidates = distinctByURL(query.getResults());

        if (candidates.size() < 1)
            throw new IOException("Did not receive enough candidates (size < 1)");

        return candidates;
    }

    private List<Content> generateContent() throws IOException {
        ArrayList<Content> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            // Query ContentService for new Content text.
            String query = ContentService.getInstance()
                    .getContent("", "")
                    .execute().body();

            if (query == null)
                throw new IOException("Did not receive Content (is null)");

            // Pick random Content owner.
            Person randy = friends.get(new Random().nextInt(friends.size()));

            // Find last post time or set it to now.
            long lastPostTime = list.size() > 0
                    ? list.get(list.size() - 1).getPostedAt()
                    : System.currentTimeMillis();
            // Assign random delay between posts.
            int timePassed = random.nextInt(1000 * 60 * 60 * 24);
            // Create new Content and add it to list.
            Content content = new Content(randy, query, lastPostTime - timePassed);
            list.add(content);
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
    public boolean signin(String email, String password) throws IOException {
        // Simulate network work.
        simulateWork();
        // This should do for now.
        signedIn = email.equals("qwe@asd.yxc") && password.equals("qweasd");
        return signedIn;
    }

    @Override
    public void signout() {
        // Not simulating work because this should run on async thread.
//        simulateWork();
        // Remove all data assigned to user.
        signedIn = false;
        user = null;
        friends = null;
        contents = null;
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    @Override
    public boolean signup(String firstName,
                          String lastName,
                          String email,
                          String password) throws IOException {
        // Simulate network work.
        simulateWork();
        // Anyone whose first name starts with Q is welcomed.
        signedIn = firstName.toLowerCase().startsWith("q");
        return signedIn;
    }

    @Override
    public Boolean uploadContent(String text) throws IOException {
        // Simulate network work.
        simulateWork();
        // Create new Content.
        Content content = new Content(user, text, System.currentTimeMillis());
        // Create new Content list with new Content in top.
        ArrayList<Content> newContents = new ArrayList<>();
        newContents.add(content);
        newContents.addAll(contents);
        // Replace old Contents with new Contents.
        contents = newContents;
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

    private void simulateWork() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignored) {}
    }
}
