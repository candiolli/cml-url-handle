package com.newswhip.cmlurlhandle.util;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DomainUtilTest {

    @Test
    void mustGetDomainNameFromUrl() throws URISyntaxException {
        String domainName = DomainUtil.getDomainName("http://www.rte.ie/news/politics/2018/1004/1001034-cso");
        assertEquals("rte.ie", domainName);

        domainName = DomainUtil.getDomainName("http://www.bbc.com/news/world-europe-45746837");
        assertEquals("bbc.com", domainName);

        domainName = DomainUtil.getDomainName("https://www.rte.ie/news/ulster/2018/1004/1000952-moanghan-mine");
        assertEquals("rte.ie", domainName);
    }

}