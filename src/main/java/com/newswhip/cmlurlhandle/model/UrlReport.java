package com.newswhip.cmlurlhandle.model;

import java.util.StringJoiner;

public class UrlReport {
    private String domain;
    private int qtdUrls;
    private Integer socialScore;

    public UrlReport(String domain, int qtdUrls, Integer socialScore) {
        this.domain = domain;
        this.qtdUrls = qtdUrls;
        this.socialScore = socialScore;
    }
    public UrlReport(String domain, Integer socialScore) {
        this.domain = domain;
        this.socialScore = socialScore;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getSocialScore() {
        return socialScore;
    }

    public void setSocialScore(Integer socialScore) {
        this.socialScore = socialScore;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UrlReport.class.getSimpleName() + "[", "]")
                .add("domain='" + domain + "'")
                .add("socialScore=" + socialScore)
                .toString();
    }

    public int getQtdUrls() {
        return qtdUrls;
    }

    public void setQtdUrls(int qtdUrls) {
        this.qtdUrls = qtdUrls;
    }


}
