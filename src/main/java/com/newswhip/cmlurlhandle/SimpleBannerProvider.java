package com.newswhip.cmlurlhandle;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleBannerProvider extends DefaultBannerProvider {

    public String getBanner() {
        StringBuffer buf = new StringBuffer("");
        return buf.toString();
    }

    public String getVersion() {
        return "1.0.0";
    }

    public String getWelcomeMessage() {
        return "Welcome to Newswhip URL Handle CLI";
    }

    public String getProviderName() {
        return "Newswhip URL Handle CLI";
    }

}
