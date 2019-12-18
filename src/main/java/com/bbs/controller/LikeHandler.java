package com.bbs.controller;


import com.bbs.entity.Like;
import com.bbs.entity.User;
import com.bbs.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/Like")
@Controller
public class LikeHandler {
    ILikeService likeService;

    @Autowired
    public void setLikeService(ILikeService likeService) {
        this.likeService = likeService;
    }

    @RequestMapping("/createLike")
    @ResponseBody
    public Map createLike(Like like, HttpSession session) {
        Map<String,String> result = new HashMap<>();
        try{
            User user = (User)session.getAttribute("user");
            like.setUserId(user.getUserId());
            likeService.createLike(like);
            result.put("message","success");
        } catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }
}
