package com.newswhip.cmlurlhandle.util;

import java.net.URI;
import java.net.URISyntaxException;

public class DomainUtil {
    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String host = uri.getHost();
        return host != null && host.startsWith("www.") ? host.substring(4) : host;
    }
}
