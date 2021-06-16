package io.github.agrawald.censys.v1;

import io.github.agrawald.censys.GenericApiTest;
import io.github.agrawald.censys.exception.CensysException;
import io.github.agrawald.censys.v1.model.ReportOpRequest;
import io.github.agrawald.censys.v1.model.Type;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReportApiV1Test extends GenericApiTest {

    @Test
    void shouldGetAValidIpv4Response() throws IOException, InterruptedException, CensysException {
        final ReportApiV1 api = censysBuilder.build().reportApiV1();
        final JsonNode response = api.search(Type.ipv4, ReportOpRequest.builder()
                .buckets(10)
                .field("location.country_code")
                .query("80.http.get.headers.server: nginx")
                .build());
        assertNotNull(response);
    }

    @Test
    void shouldGetAValidWebsiteResponse() throws IOException, InterruptedException, CensysException {
        final ReportApiV1 api = censysBuilder.build().reportApiV1();
        final JsonNode response = api.search(Type.websites, ReportOpRequest.builder()
                .buckets(10)
                .field("25.smtp.starttls.tls.cipher_suite.name")
                .query("facebook.com")
                .build());
        assertNotNull(response);
    }

    @Test
    void shouldGetAValidCertificateResponse() throws IOException, InterruptedException, CensysException {
        final ReportApiV1 api = censysBuilder.build().reportApiV1();
        final JsonNode response = api.search(Type.certificates, ReportOpRequest.builder()
                .buckets(10)
                .field("parsed.subject.locality")
                .query("parsed.issuer.organization: cloudflare")
                .build());
        assertNotNull(response);
    }
}