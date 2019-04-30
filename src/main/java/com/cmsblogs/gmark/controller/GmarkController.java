package com.cmsblogs.gmark.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gmark")
public class GmarkController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
