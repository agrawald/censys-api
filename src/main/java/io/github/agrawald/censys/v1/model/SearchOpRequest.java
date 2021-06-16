package io.github.agrawald.censys.v1.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchOpRequest implements OpRequest {
    private String query;
    private int page;
    private boolean flatten;
    private String[] fields;
}
