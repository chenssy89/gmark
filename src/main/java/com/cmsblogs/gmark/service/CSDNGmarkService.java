package com.cmsblogs.gmark.service;

import org.jsoup.nodes.Document;

public class CSDNGmarkService extends AbstractGmarkService{


    @Override
    protected String getHtmlContent(Document document) {
        return document.getElementById("mainBox").getElementById("content_views").html();
    }
}
