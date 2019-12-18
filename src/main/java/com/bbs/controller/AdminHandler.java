package com.bbs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/Admin")
@Controller
public class AdminHandler {
    @GetMapping("/create")
    public String create(){
        return "create";
    }

    @PostMapping("/deleteCommentByCommentId")
    public Map deleteCommentByCommentId(Long CommentId) {
        return new HashMap();
    }
}
