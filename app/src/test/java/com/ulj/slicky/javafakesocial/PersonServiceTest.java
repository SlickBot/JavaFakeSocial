package com.ulj.slicky.javafakesocial;

import com.ulj.slicky.javafakesocial.model.person.PersonQuery;
import com.ulj.slicky.javafakesocial.rest.ApiServices;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by SlickyPC on 17.5.2017
 */
public class PersonServiceTest {

    private PersonQuery query;

    @Before
    public void can_receive_query() throws Exception {
        query = ApiServices.personApi()
                .getPerson(5, null, "1", null, null)
                .execute()
                .body();
    }

    @Test
    public void query_is_not_null() {
        assertNotNull("After querying person query should not be null.", query);
    }

    @Test
    public void query_contains_results() {
        assertNotNull("Query results should not be null.", query.getResults());
        assertEquals("Query results should contain 5 items.", 5, query.getResults().size());
    }

}
