# censys-api

A Java 11 implementation for the Censys API for V1 and V2

V1 API Documentation: https://censys.io/api
V2 API Documentation: https://search.censys.io/api

# compile

> mvn clean compile

# test

> mvn clean test -Dusername=<api_id> -Dpassword=<api_seceret>

# dependency

```xml
<dependency>
    <groupId>au.com.mayi</groupId>
    <artifactId>censys-api</artifactId>
    <version>1.0</version>
</dependency>
```

# usage

1. Create a Censys API builder instance

- without proxy

```java
final Censys censys=Censys.builder()
        .rootUri("https://search.censys.io/api")
        .password("<api_secret>")
        .username("<api_id>")
        .build();
```

- with proxy

```java
final InetSocketAddress address=new InetSocketAddress("<proxy_host>",<proxy_port>);
final Censys censys=Censys.builder()
        .rootUri("https://search.censys.io/api")
        .password("<api_secret>")
        .username("<api_id>")
        .proxy(new Proxy(Type.HTTP,address))
        .build();
```

## account V1 api

```java
final AccountApiV1 api=censys.accountApiV1();
final JsonNode response=api.info();
```

## bulk V1 api

```java
final BulkApiV1 api=censysBuilder.build().bulkApiV1();
final JsonNode response=api.certificates(BulkOpRequest.builder()
        .fingerprints(new String[]{
        "13a88367a15e4e0e9d77158c95e6718d9158ac1bc30619f29ba7bf7d5befc50f"
        })
        .build());
```

## data V1 api

```java
final DataApiV1 api=censysBuilder.build().dataApiV1();
final JsonNode response=api.data();
```

## report V1 api

### ipv4

```java
final ReportApiV1 api=censysBuilder.build().reportApiV1();
final JsonNode response=api.search(Type.ipv4,ReportOpRequest.builder()
        .buckets(10)
        .field("location.country_code")
        .query("80.http.get.headers.server: nginx")
        .build());
```

### websites

```java
final ReportApiV1 api=censysBuilder.build().reportApiV1();
final JsonNode response=api.search(Type.websites,ReportOpRequest.builder()
        .buckets(10)
        .field("25.smtp.starttls.tls.cipher_suite.name")
        .query("facebook.com")
        .build());
```

### certificates

```java
 final ReportApiV1 api=censysBuilder.build().reportApiV1();
final JsonNode response=api.search(Type.certificates,ReportOpRequest.builder()
        .buckets(10)
        .field("parsed.subject.locality")
        .query("parsed.issuer.organization: cloudflare")
        .build());
```

## search V1 api

### ipv4

```java
final SearchApiV1 api=censys.searchApiV1();
final JsonNode response=api.search(Type.ipv4,SearchOpRequest.builder()
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
```

### websites

```java
final SearchApiV1 api=censys.searchApiV1();
final JsonNode response=api.search(Type.ipv4,SearchOpRequest.builder()
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
```

### certificates

```java
final SearchApiV1 api=censys.build().searchApiV1();
final JsonNode response=api.search(Type.certificates,SearchOpRequest.builder()
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
```

## view V1 api

### ipv4

```java
final ViewApiV1 api=censys.viewApiV1();
final JsonNode response=api.view(Type.ipv4,"8.8.8.8");
```

### websites

```java
final ViewApiV1 api=censys.viewApiV1();
final JsonNode response=api.view(Type.websites,"facebook.com");
```

### certificates

```java
final ViewApiV1 api=censys.viewApiV1();
final JsonNode response=api.view(Type.certificates,"c06c035a35464d32912584500de796ffec8148ac2704b5d84bc833154f79f6d0");
```

## hosts V2 api

### view

```java
final HostsApiV2 api=censys.hostsApiV2();
final JsonNode response=api.view("8.8.8.8",LocalDateTime.now().minusDays(1));
```

### search

```java
final HostsApiV2 api=censys.hostsApiV2();
final JsonNode response=api.search("ip:8.8.8.8",1,null);
```

### aggregate

```java
final HostsApiV2 api=censys.hostsApiV2();
final JsonNode response = api.aggregate("services.port", "service.service_name: HTTP", 50);
```

