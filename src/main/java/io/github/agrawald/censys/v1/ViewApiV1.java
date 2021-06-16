package io.github.agrawald.censys.v1;

import io.github.agrawald.censys.GenericApi;
import io.github.agrawald.censys.exception.CensysException;
import io.github.agrawald.censys.v1.model.Type;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.Proxy;

public class ViewApiV1 extends GenericApi {
    private static final String URI_PATTERN = "/view/%s/%s";

    public ViewApiV1(String rootUri, String username, String password, Proxy proxy) {
        super(rootUri, username, password, proxy);
    }

    public JsonNode view(Type type, String value) throws IOException, InterruptedException, CensysException {
        return this.get(String.format(URI_PATTERN, type.name(), value));
    }
}

