package com.newswhip.cmlurlhandle.dao;

import com.newswhip.cmlurlhandle.model.UrlReport;

import java.util.HashMap;
import java.util.Map;

public class UrlDAO {

    private Map<String, Integer> urlsData = new HashMap();

    public UrlDAO() {
        urlsData.put("http://www.rte.ie/news/politics/2018/1004/1001034-cso/", 20);
        urlsData.put("https://www.rte.ie/news/ulster/2018/1004/1000952-moanghan-mine/", 30);
        urlsData.put("http://www.bbc.com/news/world-europe-45746837", 10);
    }
    public String create(UrlReport url) {
        urlsData.put(url.getDomain(), url.getSocialScore());
        return urlsData.get(url.getDomain()).toString();
    }

    public Map<String, Integer> findAll() {
        return urlsData;
    }
}
