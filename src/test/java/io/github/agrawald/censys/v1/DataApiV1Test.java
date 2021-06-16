package io.github.agrawald.censys.v1;

import io.github.agrawald.censys.GenericApiTest;
import io.github.agrawald.censys.exception.CensysException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataApiV1Test extends GenericApiTest {

    @Test
    void shouldGetAValidResponse() throws IOException, InterruptedException, CensysException {
        DataApiV1 api = censysBuilder.build().dataApiV1();
        final JsonNode response = api.data();
        assertNotNull(response);
    }

    @Test
    void shouldThrowCensysException() {
        DataApiV1 api = censysBuilder
                .password("some dummy password")
                .build().dataApiV1();
        assertThrows(CensysException.class, api::data);
    }
}