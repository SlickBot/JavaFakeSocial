package com.ulj.slicky.javafakesocial;

import com.ulj.slicky.javafakesocial.rest.ApiServices;

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
        content = ApiServices.contentApi()
                .getContent("car", "apple")
                .execute()
                .body();
    }

    @Test
    public void query_contains_results() {
        assertNotNull("Content result should not be null.", content);
        assertNotEquals("Content result should not be empty.", 0, content.length());
    }

}
