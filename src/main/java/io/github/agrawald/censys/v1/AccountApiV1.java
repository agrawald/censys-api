package io.github.agrawald.censys.v1;

import io.github.agrawald.censys.GenericApi;
import io.github.agrawald.censys.exception.CensysException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.Proxy;

public class AccountApiV1 extends GenericApi {
    private final String uri = "/account";

    public AccountApiV1(String rootUri, String username, String password, Proxy proxy) {
        super(rootUri, username, password, proxy);
    }

    public JsonNode info() throws IOException, InterruptedException, CensysException {
        return this.get(uri);
    }
}
