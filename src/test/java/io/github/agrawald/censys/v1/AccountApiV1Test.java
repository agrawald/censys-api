package io.github.agrawald.censys.v1;

import io.github.agrawald.censys.GenericApiTest;
import io.github.agrawald.censys.exception.CensysException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountApiV1Test extends GenericApiTest {

    @Test
    void shouldGetAValidResponse() throws IOException, InterruptedException, CensysException {
        AccountApiV1 accountApiV1 = censysBuilder.build().accountApiV1();
        final JsonNode response = accountApiV1.info();
        assertNotNull(response);
    }

    @Test
    void shouldThrowCensysException() throws IOException, InterruptedException, CensysException {
        AccountApiV1 accountApiV1 = censysBuilder.username("some crap username").build().accountApiV1();
        assertThrows(CensysException.class, accountApiV1::info);
    }
}