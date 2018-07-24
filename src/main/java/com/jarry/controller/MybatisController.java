package com.jarry.controller;

import com.jarry.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jarry on 2018/6/14.
 */
@RestController
public class MybatisController {

    @Autowired
    private MybatisService mybatisService;

    @RequestMapping("/tx")
    public void testTransation() {
        mybatisService.testTransation();
    }
}
