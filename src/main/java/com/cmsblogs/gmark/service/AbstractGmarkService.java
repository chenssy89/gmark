package com.cmsblogs.gmark.service;

import com.cmsblogs.gmark.pojo.GMarkPOJO;
import com.overzealous.remark.Remark;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * gmark 的抽象实现
 *
 * @author:chenssy
 * @date:2019-05-01
 */
public abstract class AbstractGmarkService implements GmarkService{


    @Override
    public String getBlogContent(GMarkPOJO gMark) {

        // 1. 获取 Document
        Document document = getDocument(gMark.getBlogUrl());

        String htmlContent = getHtmlContent(document);



        return null;
    }

    /**
     * 获取 Document 对象
     * @param blogUrl
     * @return
     */
    private Document getDocument(String blogUrl) {
        try {
            return Jsoup.connect(blogUrl).get();
        } catch (IOException e) {
            throw new RuntimeException("解析地址，获取 Document 对象失败..",e);
        }
    }


    /**
     * 每个网站的结构不同，需要各个子类完成解析
     * @param document
     * @return
     */
    protected abstract String getHtmlContent(Document document);
}
