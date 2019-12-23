package com.bbs.controller;


import com.bbs.entity.Comment;
import com.bbs.entity.Like;
import com.bbs.entity.Post;
import com.bbs.entity.User;
import com.bbs.service.ICommentService;
import com.bbs.service.ILikeService;
import com.bbs.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/Like")
@Controller
public class LikeHandler {
    public ILikeService likeService;
    private IPostService postService;
    private ICommentService commentService;

    @Autowired
    public void setPostService(IPostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setCommentService(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setLikeService(ILikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/likePost")
    @ResponseBody
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
    @ResponseBody
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

    //返回喜欢的帖子
    @GetMapping("/showPosts")
    public String findLikePosts (Model model,HttpSession session) {
        List<Post> posts = new ArrayList<>();
        try{
            User user = (User) session.getAttribute("user");
            posts = postService.findLikePostsByUserId(user.getUserId(),1L);
        }catch (Exception e) {
            System.out.println("ERROR:showPosts");
        }
        model.addAttribute("posts",posts);
        return "like/showPosts";
    }

    //返回喜欢的评论
    @GetMapping("/showComments")
    public String findLikeComments(Model model,HttpSession session){
        List<Comment> comments = new ArrayList<>();
        try{
            User user = (User) session.getAttribute("user");
            comments = commentService.findLikeCommentsByUserId(user.getUserId());
        }catch (Exception e) {
            System.out.println("ERROR:showComments");
        }
        model.addAttribute("comments",comments);
        return "like/showComments";
    }
}
