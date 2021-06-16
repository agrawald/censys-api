package io.github.agrawald.censys.v1;

import io.github.agrawald.censys.GenericApiTest;
import io.github.agrawald.censys.exception.CensysException;
import io.github.agrawald.censys.v1.model.SearchOpRequest;
import io.github.agrawald.censys.v1.model.Type;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SearchApiV1Test extends GenericApiTest {
    @Test
    void shouldReturnValidIpv4Response() throws CensysException, IOException, InterruptedException {
        final SearchApiV1 api = this.censysBuilder.build().searchApiV1();
        final JsonNode response = api.search(Type.ipv4, SearchOpRequest.builder()
                .fields(new String[]{
                        "ip",
                        "updated_at",
                        "protocols",
                        "metadata.description",
                        "autonomous_system.name",

                        "23.telnet.banner",

                        "80.http.get.title",
                        "80.http.get.metadata.description",
                        "8080.http.get.metadata.description",
                        "8888.http.get.metadata.description",
                        "443.https.get.metadata.description",

                        "443.https.get.title",
                        "443.https.tls.certificate.parsed.subject_dn",
                        "443.https.tls.certificate.parsed.names",
                        "443.https.tls.certificate.parsed.subject.common_name",
                        "443.https.tls.certificate.parsed.extensions.subject_alt_name.dns_names"
                })
                .query("80.http.get.headers.server: nginx")
                .flatten(true)
                .page(1)
                .build());

        assertNotNull(response);
    }

    @Test
    void shouldReturnValidWebsitesResponse() throws CensysException, IOException, InterruptedException {
        final SearchApiV1 api = this.censysBuilder.build().searchApiV1();
        final JsonNode response = api.search(Type.ipv4, SearchOpRequest.builder()
                .fields(new String[]{
                        "ip",
                        "25.smtp.starttls.ehlo",
                        "443.https.tls.version",
                        "443.https_www.tls.certificate.parsed.issuer.organization"
                })
                .query("facebook.com")
                .flatten(true)
                .page(1)
                .build());

        assertNotNull(response);
    }

    @Test
    void shouldReturnValidCertificatesResponse() throws CensysException, IOException, InterruptedException {
        final SearchApiV1 api = this.censysBuilder.build().searchApiV1();
        final JsonNode response = api.search(Type.certificates, SearchOpRequest.builder()
                .fields(new String[]{
                        "parsed.fingerprint_sha256",
                        "parsed.subject_dn",
                        "parsed.issuer_dn",
                        "parsed.issuer.organization",
                        "parsed.validity.start",
                        "parsed.validity.end",
                        "parsed.names"
                })
                .query("parsed.issuer.common_name: \"Let's Encrypt\"")
                .flatten(true)
                .page(1)
                .build());

        assertNotNull(response);
    }
}