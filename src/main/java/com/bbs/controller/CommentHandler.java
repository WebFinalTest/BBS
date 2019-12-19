package com.bbs.controller;

import com.bbs.entity.Comment;
import com.bbs.entity.Post;
import com.bbs.service.ICommentService;
import com.bbs.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/Comment")
@Controller
public class CommentHandler {
    private ICommentService commentService;
    private IPostService postService;

    @Autowired
    public void setCommentService(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setPostService(IPostService postService) {
        this.postService = postService;
    }

    //跳转到某个帖子的里面
    @GetMapping("/goPostByPostId/{postId}")
    public String goComments(@PathVariable("postId") Long postId, Model model){
        List<Comment> comments = new ArrayList();
        Post post = new Post();
        try {
            comments = commentService.findByPostId(postId,1L);
            post = postService.findPostByPostId(postId);
        }catch (Exception e) {

        }
        model.addAttribute("comments",comments);
        model.addAttribute("post",post);
        return "/post/show";
    }
}
