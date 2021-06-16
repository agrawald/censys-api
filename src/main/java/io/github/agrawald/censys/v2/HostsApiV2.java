package io.github.agrawald.censys.v2;

import io.github.agrawald.censys.GenericApi;
import io.github.agrawald.censys.exception.CensysException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.Proxy;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HostsApiV2 extends GenericApi {
    private static final String VIEW_URI = "/hosts/%s";
    private static final String SEARCH_URI = "/hosts/search";
    private static final String AGGREGATE_URI = "/hosts/aggregate?field=%s";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    public HostsApiV2(String rootUri, String username, String password, Proxy proxy) {
        super(rootUri, username, password, proxy);
    }

    public JsonNode view(String host, LocalDateTime atTime) throws IOException, InterruptedException, CensysException {
        String strUri = String.format(VIEW_URI, host);
        if (atTime != null) {
            strUri += "?at_time=" + FORMATTER.format(atTime);
        }
        return this.get(strUri);
    }

    public JsonNode search(String query, int perPage, String cursor) throws IOException, InterruptedException, CensysException {
        final StringBuilder sb = new StringBuilder(SEARCH_URI);
        boolean hasQueryParam = false;
        if (query != null && !query.isBlank()) {
            sb.append("?q=").append(URLEncoder.encode(query, StandardCharsets.UTF_8));
            hasQueryParam = true;
        }
        if (perPage > 0) {
            sb.append(hasQueryParam ? "&" : "?")
                    .append("per_page=")
                    .append(perPage);
            hasQueryParam = true;
        }
        if (cursor != null && !cursor.isBlank()) {
            sb.append(hasQueryParam ? "&" : "?")
                    .append("cursor=")
                    .append(cursor);
        }
        return this.get(sb.toString());
    }

    public JsonNode aggregate(String field, String query, int numBuckets) throws IOException, InterruptedException, CensysException {
        final StringBuilder sb = new StringBuilder(String.format(AGGREGATE_URI, field));
        if (query != null && !query.isBlank()) {
            sb.append("&q=").append(URLEncoder.encode(query, StandardCharsets.UTF_8));
        }

        if (numBuckets > 0) {
            sb.append("&num_buckets=").append(numBuckets);
        }
        return this.get(sb.toString());
    }
}
