package io.github.agrawald.censys.v2;

import io.github.agrawald.censys.exception.CensysException;
import io.github.agrawald.censys.GenericApiTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HostsApiV2Test extends GenericApiTest {
    @Test
    void shouldReturnAValidSearchResponse() throws CensysException, IOException, InterruptedException {
        final HostsApiV2 api = this.censysBuilder.build().hostsApiV2();
        final JsonNode response = api.search("ip:8.8.8.8", 1, null);
        assertNotNull(response);
    }
    @Test
    void shouldReturnAValidAggregateResponse() throws CensysException, IOException, InterruptedException {
        final HostsApiV2 api = this.censysBuilder.build().hostsApiV2();
        final JsonNode response = api.aggregate("services.port", "service.service_name: HTTP", 50);
        assertNotNull(response);
    }
    @Test
    void shouldReturnAValidViewResponse() throws CensysException, IOException, InterruptedException {
        final HostsApiV2 api = this.censysBuilder.build().hostsApiV2();
        final JsonNode response = api.view("8.8.8.8", LocalDateTime.now().minusDays(1));
        assertNotNull(response);
    }
}