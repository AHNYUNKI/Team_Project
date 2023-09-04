package com.api.shop_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"","/index"})
    public String index(){
        return "/index";
    }

    @GetMapping("/login")
    public String login(){
        return "/member/login";
    }

    @GetMapping("/join")
    public String join(){
        return "/member/join";
    }

    @GetMapping("/memberList")
    public String memberList(){
        return "/member/memberList";
    }

    @GetMapping("/insert")
    public String insert(){
        return "/item/insert";
    }

    @GetMapping("/post")
    public String post(){
        return "/post/postList";
    }

    @GetMapping("/itemList")
    public String itemList(){
        return "/item/itemList";
    }



}
