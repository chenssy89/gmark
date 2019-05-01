package com.cmsblogs.gmark.controller;


import com.cmsblogs.gmark.pojo.GMarkPOJO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String mark(@RequestBody GMarkPOJO gMark){

        System.out.println(gMark);

        return "success";
    }
}
