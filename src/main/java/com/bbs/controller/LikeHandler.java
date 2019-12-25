package com.bbs.controller;


import com.bbs.entity.Comment;
import com.bbs.entity.Like;
import com.bbs.entity.Post;
import com.bbs.entity.User;
import com.bbs.service.ICommentService;
import com.bbs.service.ILikeService;
import com.bbs.service.IPostService;
import com.bbs.service.IUserService;
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
    private ILikeService likeService;
    private IPostService postService;
    private ICommentService commentService;
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

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
        Map<Long,String> userNames = new HashMap<>();
        Long postPages= 0L;
        try{
            User user = (User) session.getAttribute("user");
            postPages = (long)likeService.findPostLikes(user.getUserId()).size();
            posts = postService.findLikePostsByUserId(user.getUserId(),1L);
            for (Post post:posts) {
                if(userNames.get(post.getUserId()) == null) {
                    userNames.put(post.getUserId(),userService.findByUserId(post.getUserId()).getUserName());
                }
            }
        }catch (Exception e) {
            System.out.println("ERROR:showPosts");
        }
        model.addAttribute("userNames",userNames);
        model.addAttribute("postPages",postPages);
        model.addAttribute("posts",posts);
        return "like/showPosts";
    }

    //返回喜欢的评论
    @GetMapping("/showComments")
    public String findLikeComments(Model model,HttpSession session){
        List<Comment> comments = new ArrayList<>();
        Map<Long,String> userNames = new HashMap<>();
        Long commentPages= 0L;
        try{
            User user = (User) session.getAttribute("user");
            commentPages = (long)likeService.findCommentLikes(user.getUserId()).size();
            comments = commentService.findLikeCommentsByUserId(user.getUserId());
            for (Comment comment:comments) {
                if(userNames.get(comment.getUserId()) == null) {
                    userNames.put(comment.getUserId(),userService.findByUserId(comment.getUserId()).getUserName());
                }
            }
        }catch (Exception e) {
            System.out.println("ERROR:showComments");
        }
        model.addAttribute("userNames",userNames);
        model.addAttribute("commentPages",commentPages);
        model.addAttribute("comments",comments);
        return "like/showComments";
    }
}
