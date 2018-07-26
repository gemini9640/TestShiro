package com.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);subject.getSession();
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            return "loginfail";
        }
        return "/loginsuccess";
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public String logout() {
        return "login";
    }

//    @RequiresRoles("admin")
    @RequestMapping(value = "/authr/adminRole", method = RequestMethod.GET)
    @ResponseBody
    public String testRole() {
        return "adminRole success";
    }

//    @RequiresRoles("admin1")
    @RequestMapping(value = "/authr/admin1Role", method = RequestMethod.GET)
    @ResponseBody
    public String testRole1() {
        return "admin1Role success";
    }

//    @RequiresPermissions("user:update")
    @RequestMapping(value = "/authr/updatePermission", method = RequestMethod.GET)
    @ResponseBody
    public String updatePermission() {
        return "updatePermission success";
    }

//    @RequiresPermissions("user:select")
    @RequestMapping(value = "/authr/selectPermission", method = RequestMethod.GET)
    @ResponseBody
    public String selectPermission() {
        return "selectPermission1 success";
    }

//    @RequiresPermissions("user:add")
    @RequestMapping(value = "/authr/addPermission", method = RequestMethod.GET)
    @ResponseBody
    public String addPermission() {
        return "addPermission success";
    }

//    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/authr/deletePermission", method = RequestMethod.GET)
    @ResponseBody
    public String deletePermission1() {
        return "deletePermission1 success";
    }

}
