package io.github.agrawald.censys;

import org.junit.jupiter.api.BeforeEach;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class GenericApiTest {
    protected Censys.CensysBuilder censysBuilder;

    @BeforeEach
    void beforeEach() {
        final InetSocketAddress address = new InetSocketAddress("jailbird.cp.pacs", 3128);
        censysBuilder = Censys.builder()
                .rootUri("https://search.censys.io/api")
                .password(System.getenv("password"))
                .username(System.getenv("username"))
                .proxy(new Proxy(Proxy.Type.HTTP, address));
    }

}
