package com.hehe.controller;

import com.alibaba.fastjson.JSONObject;

import com.hehe.config.TokenProvider;
import com.hehe.entity.JwtConfig;
import com.hehe.entity.R;
import com.hehe.entity.User;
import com.hehe.server.impl.UserServerImpl;
import com.hehe.util.MD5Util;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Templates;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    UserServerImpl userServer;
    @Autowired
    private TokenProvider tokenProvider;

    @GetMapping("/user")
    public String hehe(Model model) {
        model.addAttribute("user", new User(UUID.randomUUID().toString(), "yizhiwazi", "20170928"));
        return "user";
    }

    @GetMapping("/user/list")
    public String userlist(Model model) {
        List<User> userList = new ArrayList<>();
        userList.add(new User(UUID.randomUUID().toString(), "yizhiwazi", "20170928"));
        userList.add(new User(UUID.randomUUID().toString(), "kumamon", "模板渲染成功了"));
        userList.add(new User(UUID.randomUUID().toString(), "admin", "thymeleaf渲染的东西"));
        model.addAttribute("userList", userList);
        return "userList";
    }

    /**
     * 登入接口
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public R login(String username, String password){
        System.out.println(username+":"+password);
        String encrypt = MD5Util.encrypt(password);
        R r = new R();
        User login = userServer.login(username, encrypt);
        if(login != null){
            r.setMsg(R.success_state);
            HashMap<String, Object> objectHashMap = new HashMap<>();
            objectHashMap.put("username",username);
            String token = tokenProvider.createToken(objectHashMap, true);
            r.setToken(token);
            List<User> userList = new ArrayList<> ();
            userList.add(login);
            r.setData(userList);

        }else {
            r.setMsg(R.failed_state);
            r.setToken("");
            r.setData(null);
        }
        return r;
    }/**
     * 注册接口
     * 用md5进行加密
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/regist")
    public R regist(String username,String password){
        R r = new R();
        int i = userServer.check(username);
        if(i>0){
            r.setMsg(R.failed_state);
            r.setData(null);
            return r;
        }
        String encrypt = MD5Util.encrypt(password);
        int register = userServer.register(username, encrypt);
        if(register > 0){
            r.setMsg(R.success_state);
            List<Integer> userList = new ArrayList<> ();
            userList.add(register);
            r.setData(userList);
        }else {
            r.setMsg(R.failed_state);
            r.setData(null);

        }
        return r;
    }
    @RequestMapping("/apis/test")
    public R api(){
        R r = new R();
        r.setMsg(R.success_state);
        List <String> list = new ArrayList<> ();
        list.add("接口测试成功");
        r.setData(list);
        return r;
    }



}
