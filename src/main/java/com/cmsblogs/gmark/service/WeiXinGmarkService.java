package com.cmsblogs.gmark.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WeiXinGmarkService extends AbstractGmarkService{

    @Override
    protected Document getHtmlContent(Document document) {
        Element element = document.getElementById("js_content");


        return Jsoup.parse(element.html());
    }
}
