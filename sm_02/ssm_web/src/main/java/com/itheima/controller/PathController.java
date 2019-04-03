package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/path")
public class PathController {
    @RequestMapping("/pathName/{pname}")
    public String redirectPath(@PathVariable("pname") String path){
        System.out.println(path);
        return path;
    }
}
