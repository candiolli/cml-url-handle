package com.newswhip.cmlurlhandle.service;

import com.newswhip.cmlurlhandle.dao.UrlDAO;
import com.newswhip.cmlurlhandle.model.UrlReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UrlReportServiceTest {

    private UrlReportService urlReportService;

    @BeforeEach
    void setUp() {
        urlReportService = new UrlReportService();
    }

    @Test
    void mustReturnTheUrlCreated() {
        String s = urlReportService.create("https://www.google.com", "10");
        assertNotNull(s);
    }

    @Test
    void mustReturnDomainValidationError() {
        String s = urlReportService.create("w.google.com", "10");
        assertEquals("This URL is invalid.", s);
    }

    @Test
    void mustReturnSocialScoreValidationError() {
        String s = urlReportService.create("https://www.google.com", "1d");
        assertEquals("This Social Score is invalid.", s);
    }

    @Test
    void mustReturnUrlAlreadyExists() {
        String s = urlReportService.create("http://www.rte.ie/news/politics/2018/1004/1001034-cso/", "1");
        assertEquals("This URL already stored.", s);
    }

    @Test
    void mustSumDomainValuesWithSuccess() {
        Optional<UrlReport> domain = Optional.of(new UrlReport("google.com", 1, 20));
        urlReportService.sumDomainValues(20, domain);
        assertEquals(40, domain.get().getSocialScore());
    }

    @Test
    void mustSumDomainValuesWhenSocialScoreIsNull() {
        Optional<UrlReport> domain = Optional.of(new UrlReport("google.com", 1, null));
        urlReportService.sumDomainValues(20, domain);
        assertEquals(20, domain.get().getSocialScore());
    }

    @Test
    void mustReturnExistDomainOnTheList() {
        String domain = "rte.ie";
        List<UrlReport> domainList = new ArrayList();
        domainList.add(new UrlReport("rte.ie", 20));
        Optional<UrlReport> domainIfExists = urlReportService.findDomainIfExists(domainList, domain);
        assertEquals(domain, domainIfExists.get().getDomain());
    }

    @Test
    void mustNotReturnExistDomainOnTheList() {
        String domain = "bbc.com";
        List<UrlReport> domainList = new ArrayList();
        domainList.add(new UrlReport("rte.ie", 20));
        Optional<UrlReport> domainIfExists = urlReportService.findDomainIfExists(domainList, domain);
        assertTrue(domainIfExists.isEmpty());
    }

    @Test
    void mustRemoveUrlExists() {
        String s = urlReportService.remove("http://www.rte.ie/news/politics/2018/1004/1001034-cso/");
        assertEquals("URl removed with success.", s);
    }

    @Test
    void mustNotRemoveUrlExists() {
        String s = urlReportService.remove("http://www.rteee.ie/news/politics/2018/1004/1001034-cso/");
        assertEquals("URl not removed.", s);
    }

}