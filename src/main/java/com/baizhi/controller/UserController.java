package com.baizhi.controller;

import com.baizhi.entity.UserMap;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("findByProvince")
    public List<UserMap> findByProvince() {
        List<UserMap> byProvince = userService.findByProvince();
        return byProvince;
    }

    @RequestMapping("findByTime")
    public List<Integer> findByTime() {
        List<Integer> byTime = userService.findByTime();
        return byTime;
    }
}
