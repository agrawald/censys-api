package io.github.agrawald.censys;

import io.github.agrawald.censys.exception.CensysException;
import io.github.agrawald.censys.v1.model.OpRequest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.java.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Level;

@Log
public class GenericApi {
    private ObjectMapper objectMapper;
    private HttpClient httpClient;
    private String basicAuth;
    private String rootUri;

    public GenericApi(String rootUri, String username, String password, Proxy proxy) {
        this.rootUri = rootUri;
        this.initBasicAuth(username, password);
        this.initObjectMapper();
        this.initHttpClient(proxy);
    }

    private void initHttpClient(Proxy proxy) {
        final HttpClient.Builder builder = HttpClient.newBuilder();
        if (proxy.address() != null) {
            builder.proxy(ProxySelector.of((InetSocketAddress) proxy.address()));
        }
        this.httpClient = builder.build();
    }

    private void initBasicAuth(String username, String password) {
        this.basicAuth = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
    }

    private void initObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        this.objectMapper = mapper;
    }

    protected JsonNode get(final String url) throws IOException, InterruptedException, CensysException {
        final URI uri = URI.create(this.rootUri + url);
        final HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + this.basicAuth)
                .GET()
                .build();
        return this.exchange(request);
    }

    protected JsonNode post(final String url, final OpRequest opRequest) throws IOException, InterruptedException, CensysException {
        final URI uri = URI.create(this.rootUri + url);
        final HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + this.basicAuth)
                .POST(bodyPublisher(opRequest))
                .build();
        return this.exchange(request);
    }

    private JsonNode exchange(final HttpRequest request) throws IOException, InterruptedException, CensysException {
        final HttpResponse<String> httpResponse = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        final JsonNode response = this.objectMapper.readValue(httpResponse.body(), JsonNode.class);
        if (httpResponse.statusCode() >= 200 && httpResponse.statusCode() < 300) {
            return response;
        }
        log.log(Level.SEVERE, httpResponse.statusCode() + ": " + httpResponse.body());
        throw new CensysException(response);
    }

    private HttpRequest.BodyPublisher bodyPublisher(final OpRequest opRequest) throws JsonProcessingException {
        return HttpRequest.BodyPublishers.ofString(this.objectMapper.writeValueAsString(opRequest));
    }
}
