package com.itheima.controller;

import com.itheima.domain.City;
import com.itheima.domain.User;
import com.itheima.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;
    /**
     * @RequestBody:接收json数据，转换成对象类型
     * @ResponseBody:响应页面，将对象转换成json数据
     */

    @RequestMapping("/findAll")
    public @ResponseBody List<City> findAll() throws Exception {
        return cityService.findAll();
    }
}
