package io.github.agrawald.censys.v1.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BulkOpRequest implements OpRequest {
    private String[] fingerprints;
}
