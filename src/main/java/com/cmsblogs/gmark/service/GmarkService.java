package com.cmsblogs.gmark.service;


import com.cmsblogs.gmark.pojo.GMarkPOJO;

/**
 * gmark 的顶层接口
 *
 * @author:chenssy
 * @date:2019-05-01
 */
public interface GmarkService {

    /**
     * 获取博客内容
     *
     * @param gMark
     * @return
     */
    String getBlogContent(GMarkPOJO gMark);

}
