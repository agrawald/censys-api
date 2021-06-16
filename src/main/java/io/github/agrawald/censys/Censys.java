package io.github.agrawald.censys;

import io.github.agrawald.censys.v1.*;
import io.github.agrawald.censys.v2.HostsApiV2;
import lombok.Builder;

import java.net.Proxy;

@Builder
public class Censys {
    private String rootUri;
    private String username;
    private String password;
    private Proxy proxy;

    public AccountApiV1 accountApiV1() {
        return new AccountApiV1(this.rootUri + "/v1", username, password, proxy);
    }

    public BulkApiV1 bulkApiV1() {
        return new BulkApiV1(this.rootUri + "/v1", username, password, proxy);
    }

    public DataApiV1 dataApiV1() {
        return new DataApiV1(this.rootUri + "/v1", username, password, proxy);
    }

    public ReportApiV1 reportApiV1() {
        return new ReportApiV1(this.rootUri + "/v1", username, password, proxy);
    }

    public SearchApiV1 searchApiV1() {
        return new SearchApiV1(this.rootUri + "/v1", username, password, proxy);
    }

    public ViewApiV1 viewApiV1() {
        return new ViewApiV1(this.rootUri + "/v1", username, password, proxy);
    }

    public HostsApiV2 hostsApiV2() {
        return new HostsApiV2(this.rootUri + "/v2", username, password, proxy);
    }
}
