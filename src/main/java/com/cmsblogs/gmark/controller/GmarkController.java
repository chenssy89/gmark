package com.cmsblogs.gmark.controller;


import com.cmsblogs.gmark.pojo.GMarkPOJO;
import com.cmsblogs.gmark.service.GmarkFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/gmark")
public class GmarkController {

    /**
     * 进入首页
     *
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 获取文章
     * @param gMark
     * @return
     */
    @PostMapping("/mark")
    @ResponseBody
    public Map<String, String> mark(@RequestBody GMarkPOJO gMark){
        Map<String,String> resultMap = new HashMap<>();

        String markDown = null;
        try {
            markDown = GmarkFactory.getInstance().getMarkDown(gMark);

            resultMap.put("code","00000");
            resultMap.put("markdown",markDown);
        } catch (Exception e) {
            resultMap.put("code","-10001");
            resultMap.put("message",e.getMessage());
        }

        return resultMap;
    }
}
