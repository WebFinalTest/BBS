package com.bbs.controller;

import com.bbs.entity.Post;
import com.bbs.entity.User;
import com.bbs.service.ICommentService;
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

@RequestMapping("/Post")
@Controller
public class PostHandler {
    private IPostService postService;
    private ICommentService commentService;

    @Autowired
    public void setCommentService(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setPostService(IPostService postService) {
        this.postService = postService;
    }

    //跳转到帖子页面
    @GetMapping("/view")
    public String view(Model model) {
        List<Post> posts = new ArrayList<>();
        List<Post> topPosts = new ArrayList<>();
        Map<Long,Long> countComments = new HashMap<>();
        try{
            posts = postService.findPostsByPage(1L);
            topPosts = postService.findTopPostsByPage(1L);
            for (Post post: posts) {
                countComments.put(post.getPostId(),commentService.countAllCommentsByPostId(post.getPostId()));
            }
            for (Post post: topPosts) {
                countComments.put(post.getPostId(),commentService.countAllCommentsByPostId(post.getPostId()));
            }
        }catch (Exception e) {
            System.out.println("Error:view");
        }
        model.addAttribute("posts",posts);
        model.addAttribute("topPosts",topPosts);
        model.addAttribute("countComments",countComments);
        return "user/showPosts";
    }

    //传到修改帖子页面
    @GetMapping("/update/{postId}")
    public String toUpdate(@PathVariable("postId") Long postId, Model model) {
        try {
            Post post = postService.findPostByPostId(postId);
            model.addAttribute("post",post);
        }catch (Exception e) {
            System.out.println("出错！");
        }
        return "post/change";
    }

    //提交修改


    //根据页码查看帖子
    @GetMapping("view/{page}")
    @ResponseBody
    public List<Post> viewByPage(@PathVariable("page") Long page) {
        return postService.findPostsByPage(page);
    }

    //创建帖子
    @PostMapping("/create")
    @ResponseBody
    public Map<String,String> create(Post post, HttpSession session) {
        Map<String,String> result = new HashMap<>();
        try {
            User user = (User) session.getAttribute("user");
            post.setUserId(user.getUserId());
            if (postService.createPost(post))
                result.put("message","success");
            else
                result.put("message","failed");
        }catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    //删除帖子
    @PostMapping("/deleteByPostId")
    @ResponseBody
    public Map deleteByPostId(Long postId) {
        Map<String,String> result = new HashMap<>();
        try {
            postService.deletePost(postId);
            result.put("message","success");
        }catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    //加精帖子
    @PostMapping("/qualityPost")
    @ResponseBody
    public Map qualityPost(Long postId) {
        Map<String,String> result = new HashMap<>();
        try {
            postService.qualityPost(postId);
            result.put("message","success");
        }catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    //取消加精帖子
    @PostMapping("/unQualityPost")
    @ResponseBody
    public Map unQualityPost(Long postId) {
        Map<String,String> result = new HashMap<>();
        try {
            postService.unQualityPost(postId);
            result.put("message","success");
        }catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    //置顶帖子
    @PostMapping("/topPost")
    @ResponseBody
    public Map topPost(Long postId) {
        Map<String,String> result = new HashMap<>();
        try {
            postService.topPost(postId);
            result.put("message","success");
        }catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    //取消置顶帖子
    @PostMapping("/unTopPost")
    @ResponseBody
    public Map unTopPost(Long postId) {
        Map<String,String> result = new HashMap<>();
        try {
            postService.unTopPost(postId);
            result.put("message","success");
        }catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    //采纳评论
    @PostMapping("/adoptComment")
    @ResponseBody
    public Map adoptPost(Long postId,Long commentId) {
        Map<String,String> result = new HashMap<>();
        try {
            postService.adoptComment(postId,commentId);
            result.put("message","success");
        }catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    //更新帖子
    @PostMapping("/updatePost")
    @ResponseBody
    public Map updatePost (Post post){
        Map<String,String> result = new HashMap<>();
        try {
            if(postService.updatePost(post))
                result.put("message","success");
            else
                result.put("message","fail");
        }catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

}
