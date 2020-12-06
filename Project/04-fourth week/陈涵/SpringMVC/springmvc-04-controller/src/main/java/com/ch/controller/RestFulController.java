package com.ch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestFulController {

    
    @RequestMapping("/add")
    public String test1(int a, int b, Model model){
        int res = a + b;
        model.addAttribute("msg","结果：" + res);
        return "test";
    }
}
