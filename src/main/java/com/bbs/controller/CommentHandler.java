package com.bbs.controller;

import com.bbs.entity.Comment;
import com.bbs.entity.Post;
import com.bbs.entity.User;
import com.bbs.service.ICommentService;
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

@RequestMapping("/Comment")
@Controller
public class CommentHandler {
    private ICommentService commentService;
    private IPostService postService;
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

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
        String postUserName = "";
        Map<Long,Long> countFloorComments = new HashMap<>();
        Map<Long,String> commentUserNames = new HashMap<>();
        try {
            comments = commentService.findByPostId(postId,1L);
            post = postService.findPostByPostId(postId);
            postUserName = userService.findByUserId(post.getUserId()).getUserName();
            for (Comment comment: comments) {
                countFloorComments.put(comment.getCommentId(),commentService.countCommentsByFloorId(comment.getCommentId()));
                commentUserNames.put(comment.getUserId(),userService.findByUserId(comment.getUserId()).getUserName());
            }
        }catch (Exception e) {
            System.out.println("ERROR:goComments");
        }
        model.addAttribute("comments",comments);
        model.addAttribute("post",post);
        model.addAttribute("countFloorComments",countFloorComments);
        model.addAttribute("postUserName",postUserName);
        model.addAttribute("commentUserNames",commentUserNames);
        return "post/show";
    }

    //查看层级评论
    @GetMapping("/goFloorComments/{floorId}")
    public String goFloorComments(@PathVariable("floorId") Long floorId,Model model) {
        List<Comment> comments = new ArrayList<>();
        Map<Long,String> replyComments = new HashMap<>();
        try {
            Comment floorComment = commentService.findByCommentId(floorId);
            Comment replyComment;
            comments = commentService.findByFloorId(floorId,1L);
            for (Comment comment : comments) {
                if(comment.getReplyId() != floorComment.getCommentId()
                        && replyComments.get(comment.getReplyId()) == null) {
                    replyComment = commentService.findByCommentId(comment.getReplyId());
                    replyComments.put(comment.getReplyId(),replyComment.getCommentContent());
                }
            }
            comments.add(0,floorComment);
        }
        catch (Exception e) {
            System.out.println("Error:goFloorComments");
        }
        model.addAttribute("comments",comments);
        return "comment/showComment";
    }

    //创建评论
    @PostMapping("/create")
    @ResponseBody
    public Map createComment (HttpSession session,Comment comment) {
        Map<String,String> result = new HashMap<>();
        try {
            User user = (User) session.getAttribute("user");
            comment.setUserId(user.getUserId());
            commentService.createComment(comment);
            user = userService.findByUserId(user.getUserId());
            user.setPassword("");
            session.setAttribute("user",user);
            result.put("message","success");
        }catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }
}
