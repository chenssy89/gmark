package com.cmsblogs.gmark.service;

import com.cmsblogs.gmark.pojo.GMarkPOJO;

import java.util.HashMap;
import java.util.Map;

/**
 * gmark 的工厂，用于获取 gmark 实体类
 * @author:chenssy
 * @date:2019-05-01
 */
public class GmarkFactory {

    private static class SingletonHolder{
        public static GmarkFactory singleton = new GmarkFactory();
    }

    public static GmarkFactory getInstance(){
        return SingletonHolder.singleton;
    }

    private static Map<String,GmarkService> gmarkServiceMap = new HashMap<String,GmarkService>(){
        {
            put("csdn",new CSDNGmarkService());
        }
    };

    public String getMarkDown(GMarkPOJO gMark){
        return gmarkServiceMap.get(gMark.getWebsite()).getBlogContent(gMark);
    }
}
