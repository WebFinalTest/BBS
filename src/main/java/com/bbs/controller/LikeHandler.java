package com.bbs.controller;


import com.bbs.entity.Like;
import com.bbs.entity.User;
import com.bbs.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/Like")
@Controller
public class LikeHandler {
    public ILikeService likeService;

    @Autowired
    public void setLikeService(ILikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/likePost")
    public Map likePost(HttpSession session,Long postId) {
        Map<String,String> result = new HashMap<>();
        User user;
        Like like = new Like();
        try{
            user = (User)session.getAttribute("user");
            like.setPostId(postId);
            like.setUserId(user.getUserId());
            likeService.createLike(like);
            result.put("message","success");
        }catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    @PostMapping("/likeComment")
    public Map likeComment(HttpSession session, Long commentId) {
        Map<String,String> result = new HashMap<>();
        User user;
        Like like = new Like();
        try{
            user = (User)session.getAttribute("user");
            like.setCommentId(commentId);
            like.setUserId(user.getUserId());
            likeService.createLike(like);
            result.put("message","success");
        }catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }
}
