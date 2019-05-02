package com.cmsblogs.gmark.utils;

import org.jsoup.select.Elements;

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

}
