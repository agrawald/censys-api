package io.github.agrawald.censys.v1;

import io.github.agrawald.censys.GenericApi;
import io.github.agrawald.censys.exception.CensysException;
import io.github.agrawald.censys.v1.model.BulkOpRequest;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.Proxy;

public class BulkApiV1 extends GenericApi {
    private static final String URI_PATTERN = "/bulk/certificates";

    public BulkApiV1(String rootUri, String username, String password, Proxy proxy) {
        super(rootUri, username, password, proxy);
    }

    public JsonNode certificates(BulkOpRequest request) throws IOException, InterruptedException, CensysException {
        return this.post(URI_PATTERN, request);
    }
}
