package com.cmsblogs.gmark.service;

import com.cmsblogs.gmark.utils.GmarkUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CSDNGmarkService extends AbstractGmarkService{


    @Override
    protected Document getHtmlContent(Document document) {
        Element mainElement = document.getElementById("mainBox");

        // 不是 Markdown，则获取 HTML
        if(mainElement == null){
            mainElement = document.getElementById("htmledit_views");
        }

        String htmlContent = mainElement.getElementById("content_views").html();

        document = Jsoup.parse(htmlContent);

        // 去掉代码块中的行号
        Elements elements = document.getElementsByTag("pre");
        if(GmarkUtil.elementsNotEmpty(elements)){

            Elements preNumbers = null;
            for(Element element : elements){
                preNumbers = element.getElementsByClass("pre-numbering");
                if(GmarkUtil.elementsNotEmpty(preNumbers)){
                    for(Element preNumber : preNumbers){
                        // 删掉换行号
                        preNumber.remove();
                    }
                }
            }
        }

        return document;
    }
}
