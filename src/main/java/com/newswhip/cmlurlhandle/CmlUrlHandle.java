package com.newswhip.cmlurlhandle;

import com.newswhip.cmlurlhandle.service.UrlReportService;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CmlUrlHandle implements CommandMarker {

    private UrlReportService urlReportService = new UrlReportService();

    @CliCommand(value = {"ADD", "add"}, help = "Add url.")
    public String addUrl(@CliOption(key = {"", "url"}, help = "URL whose contents will be saved.", mandatory = true) String[] url/*,
                         @CliOption(key = {"", "socialScore"}, help = "Social score for URL.") Integer socialScore*/) {
        return urlReportService.create(url[0].trim(), url[1].trim());
    }

    @CliCommand(value = {"FINDALL", "fall"}, help = "Find all Add stored.")
    public String findAll() {
        return urlReportService.findAll().toString();
    }

    @CliCommand(value = {"EXPORT", "ex"}, help = "Export url stored.")
    public String export() {
        return urlReportService.export();
    }

    @CliCommand(value = {"REMOVE", "rm"}, help = "Export url stored.")
    public String remove(@CliOption(key = {"", "url"}, help = "URL whose contents will be remove.", mandatory = true) String url) {
        return urlReportService.remove(url);
    }

}
