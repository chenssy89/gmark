package com.cmsblogs.gmark.utils;

import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * 工具类
 *
 * @author:chenssy
 * @date:2019-05-02
 */
public class GmarkUtil {

    /**
     * 判断 Elements 是否为空
     * @param elements
     * @return
     */
    public static Boolean elementsNotEmpty(Elements elements){
        if(elements != null && elements.size() > 0){
            return true;
        }

        return false;
    }

    /**
     * 获取 图片 file
     *
     * @param imageFilePath
     * @param imageFileName
     * @return
     */
    public static File getImageFile(String imageFilePath,String imageFileName) throws IOException {
       File imageFile =  new File(imageFilePath + File.separator + imageFileName);

       // 图片存在，重命名该图片，一般采用在后面加 "1" 做法
        Integer i = 1,j;
       while (imageFile.exists()){
           i = imageFileName.lastIndexOf("_");

           //获取 _ 前面一位数字
           j = Integer.valueOf(imageFileName.substring(i - 1 , i));

           imageFileName = imageFileName.substring(0,i - 1) + (j + 1 ) + "_" + imageFileName.substring(i);

           imageFile = new File(imageFilePath + File.separator + imageFileName);
       }

       imageFile.createNewFile();

       return imageFile;
    }

}
