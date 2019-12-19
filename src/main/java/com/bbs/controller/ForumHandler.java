package com.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Forum")
@Controller
public class ForumHandler {

    @GetMapping("/index")
    public String toIndex() {
        return "index";
    }
}
