package com.example.ct60a2411_10;

public class WebPage {

    String pageUrl = "";

    WebPage() {}

    WebPage (String url) {
        String pageUrl = url;
    }

    public void setPageUrl(String url) {
        pageUrl = url;
    }

    public String getPageUrl() {
        return pageUrl;
    }
}
