package com.cmsblogs.gmark.service;

import com.cmsblogs.gmark.pojo.GMarkPOJO;
import com.cmsblogs.gmark.utils.GmarkUtil;
import com.overzealous.remark.Remark;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

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

        // 2.提取 Document 中的博文信息
        document = getHtmlContent(document);

        // 3.下载图片，并进行替换
        String htmlContent = convertHtml(gMark,document);

        // 转换为 markdown
        Remark remark = new Remark();

        return remark.convert(htmlContent);
    }

    /**
     * <p>1、转换为 HTML 类型</p>
     * <p>2、需要将文件中的图片下载到本地，且替换图片地址</p>
     * <p>3、处理代码块，因为 markdown 转换时没有给代码块加 ```</p>
     * @param gMark
     * @param document
     * @return
     */
    protected String convertHtml(GMarkPOJO gMark,Document document){
        if((gMark.getImagePath() != null && !"".equals(gMark.getImagePath())) &&
                (gMark.getImageUrl() != null && !"".equals(gMark.getImageUrl()))){

            // 处理图片
            handleImg(gMark,document);

            // 处理代码块
            handlePre(document);
        }

        return document.html();
    }

    /**
     * 处理代码块，主要是在代码块中的前面增加 `<p>```</p>` 即可
     * @param document
     */
    protected void handlePre(Document document){
        // 获取代码块
        Elements elements = document.getElementsByTag("pre");

        if(GmarkUtil.elementsNotEmpty(elements)){
            for(Element element : elements){
                element.prepend("<br/><p>```</p><br/>");
                element.append("<br/><p>```</p><br/>");
            }
        }
    }

    /**
     * 处理图片
     * @param gMark
     * @param document
     */
    private void handleImg(GMarkPOJO gMark,Document document) {
        // 获取所有的 img 标签
        Elements elements = document.getElementsByTag("img");

        if(elements != null && elements.size() >  0){

            // 新建文件
            File pathFile = new File(gMark.getImagePath());

            if(!pathFile.exists() && !pathFile.mkdirs()){
                throw new RuntimeException("新建目录失败...");
            }

            doHandleImg(elements,gMark);
        }
    }

    private void doHandleImg(Elements elements,GMarkPOJO gMark){
        // 下载图片
        String imageUrl,imageSrc = "";
        int name = 1;
        for(Element element : elements){
            try {

                // 下载图片
                String fileName = downImage(gMark,element,name);


                imageUrl = gMark.getImageUrl() + File.separator + fileName;
                // 替换地址
                element.attr("src",imageUrl);
                element.attr("alt",fileName);

                name++;
            } catch (IOException e) {
                System.out.println(imageSrc + "下载图片失败,cause by :" + e.getMessage());

                e.printStackTrace();
            }finally {

            }
        }
    }

    /**
     *
     * 下载图片
     *
     * @param gMark
     * @param element
     * @param name
     * @return
     * @throws IOException
     */
    private String downImage(GMarkPOJO gMark,Element element,int name) throws IOException {
        String fileName = gMark.getImageName() + "_" + name + ".png";

        File imageFile = GmarkUtil.getImageFile(gMark.getImagePath(),fileName);

        String imageSrc = element.attr("src");

        // 有些图片没有 http
        if(imageSrc.startsWith("//")){
            imageSrc = "http:" + imageSrc;
        }

        URL url = new URL(imageSrc);

        // 打开网络
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        InputStream is = null;
        OutputStream out = null;
        try {
            //获取链接的输出流
            is = connection.getInputStream();

            //根据输入流写入文件
            out = new FileOutputStream(imageFile);
            int i;
            while((i = is.read()) != -1){
                out.write(i);
            }

            return imageFile.getName();
        } finally {
            try {
                if(out != null){
                    out.close();
                }

                if(is != null){
                    is.close();
                }
            } catch (IOException e) {

            }
        }
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
    protected abstract Document getHtmlContent(Document document);
}
