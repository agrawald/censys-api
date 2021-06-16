package io.github.agrawald.censys.v1;

import io.github.agrawald.censys.GenericApiTest;
import io.github.agrawald.censys.exception.CensysException;
import io.github.agrawald.censys.v1.model.BulkOpRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BulkApiV1Test extends GenericApiTest {

    @Test
    void shouldGetAValidResponse() throws IOException, InterruptedException, CensysException {
        BulkApiV1 api = censysBuilder.build().bulkApiV1();
        final JsonNode response = api.certificates(BulkOpRequest.builder()
                .fingerprints(new String[]{
                        "13a88367a15e4e0e9d77158c95e6718d9158ac1bc30619f29ba7bf7d5befc50f"
                })
                .build());
        assertNotNull(response);
    }

    @Test
    void shouldThrowCensysException() {
        BulkApiV1 api = censysBuilder.build().bulkApiV1();
        assertThrows(CensysException.class, () -> api.certificates(BulkOpRequest.builder()
                .fingerprints(null)
                .build()));
    }
}