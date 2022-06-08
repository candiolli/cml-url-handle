package com.newswhip.cmlurlhandle.service;

import com.newswhip.cmlurlhandle.dao.UrlDAO;
import com.newswhip.cmlurlhandle.model.UrlReport;
import com.newswhip.cmlurlhandle.util.DomainUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UrlReportService {
    private UrlDAO urlDAO = new UrlDAO();
    public String create(String url, String socialScore) {
        if (getDomainByUrl(url) == null) return "This URL is invalid.";
        if (!isSocialScoreValid(socialScore)) return "This Social Score is invalid.";
        if (urlDAO.findAll().containsKey(url)) return "This URL already stored.";
        return urlDAO.create(new UrlReport(url, Integer.valueOf(socialScore)));
    }

    public Map<String, Integer> findAll() {
        return urlDAO.findAll();
    }

    public String remove(String urlToRemove) {
        Integer isRemove = urlDAO.findAll().remove(urlToRemove);
        if (isRemove != null && isRemove > 0) return "URl removed with success.";
        else return "URl not removed.";
    }

    public String export() {
        List<UrlReport> report = new ArrayList();
        Map<String, Integer> urls = urlDAO.findAll();

        urls.forEach((key, val) -> {
            String domain = getDomainByUrl(key);
            Integer socialScore = Integer.valueOf(val);
            Optional<UrlReport> domainExists = findDomainIfExists(report, domain);

            if (domainExists.isPresent()) {
                sumDomainValues(socialScore, domainExists);
            } else {
                report.add(new UrlReport(domain, 1, socialScore));
            }
        });

        return createReport(report);
    }

    protected void sumDomainValues(Integer socialScore, Optional<UrlReport> domainExists) {
        UrlReport urlReport = domainExists.get();
        urlReport.setQtdUrls(urlReport.getQtdUrls()+1);
        urlReport.setSocialScore(urlReport.getSocialScore() != null ? urlReport.getSocialScore() + socialScore : socialScore);
    }

    protected Optional<UrlReport> findDomainIfExists(List<UrlReport> report, String domain) {
        if (report != null) {
            return report.stream()
                    .filter(re -> re.getDomain().equals(domain))
                    .findFirst();
        }
        return Optional.empty();
    }

    private String createReport(List<UrlReport> report) {
        StringBuilder sb = new StringBuilder();
        sb.append("domain;urls;social_score\n");
        report.forEach(r -> {
            sb.append(r.getDomain()).append(";").append(r.getQtdUrls()).append(";").append(r.getSocialScore()).append("\n");
        });
        return sb.toString();
    }

    private String getDomainByUrl(String url) {
        try {
            return DomainUtil.getDomainName(url);
        } catch (URISyntaxException e) {
            return null;
        }
    }
    private boolean isSocialScoreValid(String socialScore) {
        try {
            Integer.valueOf(socialScore.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
