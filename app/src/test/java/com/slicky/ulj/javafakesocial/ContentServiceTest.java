package com.slicky.ulj.javafakesocial;

import com.slicky.ulj.javafakesocial.rest.content.ContentService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by SlickyPC on 17.5.2017
 */
public class ContentServiceTest {
    private String content;

    @Before
    public void can_receive_query() throws Exception {
        content = ContentService.getInstance().getContent(
                "car",
                "apple"
        ).execute().body();
    }

    @Test
    public void query_contains_results() {
        assertNotNull("Content result should not be null.", content);
        assertNotEquals("Content result should not be empty.", 0, content.length());
    }
}
