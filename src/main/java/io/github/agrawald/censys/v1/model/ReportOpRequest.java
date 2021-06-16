package io.github.agrawald.censys.v1.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReportOpRequest implements OpRequest {
    private String query;
    private String field;
    private int buckets;
}
