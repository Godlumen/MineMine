package com.venti.controller;

import com.venti.shiro.ShiroService;
import com.venti.shiro.filter.NotAuthc;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ShiroService shiroService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test is ok!";
    }

    @RequestMapping(value = "/updatePerms", method = RequestMethod.GET)
    public void updatePerms() {
        shiroService.updatePermission();
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public String testDel() {
        return "I can Delete!";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String testAdd() {
        return "I can add!";
    }
}
