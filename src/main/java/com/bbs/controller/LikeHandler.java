package com.bbs.controller;


import com.bbs.entity.Like;
import com.bbs.entity.User;
import com.bbs.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/Like")
@Controller
public class LikeHandler {
    private ILikeService likeService;

    @Autowired
    public void setLikeService(ILikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/test")
    public String login() {
        return "testLikeHandler";
    }

    //点赞
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

    //查看点赞过的帖子
    @GetMapping("/viewPosts")
    public String findPostLikes(HttpSession session, Model model){
        List<Like> result;
        try{
            User user=(User)session.getAttribute("user");
            result=likeService.findPostLikes(user.getUserId());
        } catch (Exception e){
            result=null;
        }
        model.addAttribute("likes",result);
        return "like/showPosts";
    }

    //查看帖子的点赞数,如抛出异常"countPostLikes"的值为-1
    @PostMapping("/countPostLikes")
    @ResponseBody
    public Map countPostLikes(Long postId){
        Map<String,Long> result = new HashMap<>();
        try{
            result.put("countPostLikes",likeService.countPostLikes(postId));
        } catch (Exception e){
            result.put("countPostLikes",-1L);
        }
        return result;
    }

    //查看帖子是否点赞过
    @PostMapping("/isPostLike")
    @ResponseBody
    public Map isPostLike(Long postId){
        Map<String,String> result = new HashMap<>();
        try{
            if(likeService.isPostLike(postId)){
                result.put("message","liked");
            }
            else {
                result.put("message","notLike");
            }
        } catch (Exception e){
            result.put("message","error");
        }
        return result;
    }

    //查看点赞过的评论
    @PostMapping("/viewComments")
    public String findCommentLikes(HttpSession session,Model model){
        List<Like> result;
        try{
            User user=(User)session.getAttribute("user");
            result=likeService.findCommentLikes(user.getUserId());
        } catch (Exception e){
            result=null;
        }
        model.addAttribute("likes",result);
        return "like/showComments";
    }

    //查看帖子评论的点赞数,如抛出异常"countCommentLikes"的值为-1
    @PostMapping("/countCommentLikes")
    @ResponseBody
    public Map countCommentLikes(Long commentId){
        Map<String,Long> result = new HashMap<>();
        try{
            result.put("countCommentLikes",likeService.countCommentLikes(commentId));
        } catch (Exception e){
            result.put("countCommentLikes",-1L);
        }
        return result;
    }

    //查看评论是否点赞过
    @PostMapping("/isCommentLike")
    @ResponseBody
    public Map isCommentLike(Long commentId){
        Map<String,String> result = new HashMap<>();
        try{
            if(likeService.isCommentLike(commentId)){
                result.put("message","liked");
            }
            else{
                result.put("message","notLike");
            }
        }catch (Exception e){
            result.put("message","error");
        }
        return result;
    }

    //删除点赞
    @PostMapping("/deleteLike")
    @ResponseBody
    public Map deleteLike(Like like,HttpSession session){
        Map<String,String> result = new HashMap<>();
        try{
            User user = (User)session.getAttribute("user");
            like.setUserId(user.getUserId());
            if(like.getCommentId()!=null){
                likeService.deleteLikeByCommentId(like.getCommentId(),like.getUserId());
            }
            else{
                likeService.deleteLikeByPostId(like.getPostId(),like.getUserId());
            }
            result.put("message","success");
        } catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }
}
