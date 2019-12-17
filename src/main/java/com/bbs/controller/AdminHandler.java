package com.bbs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Admin")
@Controller
public class AdminHandler {
    @GetMapping("/create")
    public String create(){
        return "create";
    }

}
