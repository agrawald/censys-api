package io.github.agrawald.censys.v1;

import io.github.agrawald.censys.GenericApiTest;
import io.github.agrawald.censys.exception.CensysException;
import io.github.agrawald.censys.v1.model.Type;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ViewApiV1Test extends GenericApiTest {
    @Test
    void shouldReturnAValidIpv4Response() throws CensysException, IOException, InterruptedException {
        final ViewApiV1 api = this.censysBuilder.build().viewApiV1();
        final JsonNode response = api.view(Type.ipv4, "8.8.8.8");
        assertNotNull(response);
    }

    @Test
    void shouldReturnAValidWebsitesResponse() throws CensysException, IOException, InterruptedException {
        final ViewApiV1 api = this.censysBuilder.build().viewApiV1();
        final JsonNode response = api.view(Type.websites, "facebook.com");
        assertNotNull(response);
    }

    @Test
    void shouldReturnAValidCertificatesResponse() throws CensysException, IOException, InterruptedException {
        final ViewApiV1 api = this.censysBuilder.build().viewApiV1();
        final JsonNode response = api.view(Type.certificates, "c06c035a35464d32912584500de796ffec8148ac2704b5d84bc833154f79f6d0");
        assertNotNull(response);
    }
}