package com.bbs.controller;

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

@RequestMapping("/Post")
@Controller
public class PostHandler {
    private IPostService postService;
    private ICommentService commentService;
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


    //跳转到帖子页面
    @GetMapping("/view")
    public String view(Model model) {
        List<Post> posts = new ArrayList<>();
        Long postPages = 0L,topPostPages = 0L;
        List<Post> topPosts = new ArrayList<>();
        Map<Long,Long> countComments = new HashMap<>();
        Long postSize = 0L,topPostSize = 0L;
        Map<Long,String> userNames = new HashMap<>();
        try{
            posts = postService.findUnTopPostsByPage(1L);
            topPosts = postService.findTopPostsByPage(1L);
            postPages = postService.countUnTopPostsPage();
            topPostPages = postService.countTopPostsPage();
            postSize = postService.countUnTopPosts();
            topPostSize = postService.countTopPosts();
            for (Post post: posts) {
                countComments.put(post.getPostId(),commentService.countAllCommentsByPostId(post.getPostId()));
                if(userNames.get(post.getUserId()) == null) {
                    userNames.put(post.getUserId(),userService.findByUserId(post.getUserId()).getUserName());
                }
            }
            for (Post post: topPosts) {
                countComments.put(post.getPostId(),commentService.countAllCommentsByPostId(post.getPostId()));
                if(userNames.get(post.getUserId()) == null) {
                    userNames.put(post.getUserId(),userService.findByUserId(post.getUserId()).getUserName());
                }
            }
        }catch (Exception e) {
            System.out.println("Error:view");
        }
        model.addAttribute("userNames",userNames);
        model.addAttribute("postSize",postSize);
        model.addAttribute("topPostSize",topPostSize);
        model.addAttribute("postPages",postPages);
        model.addAttribute("topPostPages",topPostPages);
        model.addAttribute("posts",posts);
        model.addAttribute("topPosts",topPosts);
        model.addAttribute("countComments",countComments);
        return "post/showPosts";
    }

    //查看置顶帖子
    @PostMapping("/viewTopPosts")
    @ResponseBody
    public List viewTopPosts(Long page) {
        List<Post> topPosts;
        List<PostInfo> postInfos = new ArrayList<>();
        try {
            topPosts = postService.findTopPostsByPage(page);
            for (Post post:topPosts) {
                PostInfo postInfo = new PostInfo();
                postInfo.post = post;
                postInfo.userName = userService.findByUserId(post.getUserId()).getUserName();
                postInfos.add(postInfo);
            }
        }catch (Exception e) {
            System.out.println("ERROR:viewTopPosts");
        }
        return postInfos;
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
    @PostMapping("/update/do")
    @ResponseBody
    public Map updatePost(Post post) {
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


    //根据页码查看帖子
    @PostMapping("/view")
    @ResponseBody
    public List viewByPage(Long page) {
        List<Post> unTopPosts;
        List<PostInfo> postInfos = new ArrayList<>();
        try {
            unTopPosts = postService.findUnTopPostsByPage(page);
            for (Post post:unTopPosts) {
                PostInfo postInfo = new PostInfo();
                postInfo.post = post;
                postInfo.userName = userService.findByUserId(post.getUserId()).getUserName();
                postInfos.add(postInfo);
            }
        }catch (Exception e) {
            System.out.println("ERROR:viewByPage");
        }
        return postInfos;
    }


    //跳到创建帖子界面
    @GetMapping("/createPost")
    public String toCreate(){
        return "post/create";
    }

    //创建帖子
    @PostMapping("/create")
    @ResponseBody
    public Map<String,String> create(Post post, HttpSession session) {
        Map<String,String> result = new HashMap<>();
        try {
            User user = (User) session.getAttribute("user");
            post.setUserId(user.getUserId());
            if (postService.createPost(post)){
                user = userService.findByUserId(user.getUserId());
                user.setPassword("");
                session.setAttribute("user",user);
                result.put("message","success");
            }
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
            if(postService.qualityPost(postId))
                result.put("message","success");
            else
                result.put("message","fail");
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
            if(postService.unQualityPost(postId))
                result.put("message","success");
            else
                result.put("message","fail");
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
            if(postService.topPost(postId))
                result.put("message","success");
            else
                result.put("message","fail");
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
            if(postService.unTopPost(postId))
                result.put("message","success");
            else
                result.put("message","fail");
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

//    //更新帖子
//    @PostMapping("/updatePost")
//    @ResponseBody
//    public Map updatePost (Post post){
//        Map<String,String> result = new HashMap<>();
//        try {
//            if(postService.updatePost(post))
//                result.put("message","success");
//            else
//                result.put("message","fail");
//        }catch (Exception e) {
//            result.put("message","error");
//        }
//        return result;
//    }

    //查看我创建的帖子
    @GetMapping("/myPosts")
    public String showMyPosts(HttpSession session,Model model){
        List<Post> posts = new ArrayList<>();
        Map<Long,Long> countComments = new HashMap<>();
        try{
            User user = (User)session.getAttribute("user");
            posts = postService.findPostsByPageUserId(user.getUserId(),1L);
            for (Post post: posts) {
                countComments.put(post.getPostId(),commentService.countAllCommentsByPostId(post.getPostId()));
            }
        }catch (Exception e) {
            System.out.println("ERROR:showMyPosts");
        }
        model.addAttribute("posts",posts);
        model.addAttribute("countComments",countComments);
        return "/user/showPosts";
    }

    //传回帖子和评论数的包装类
    class PostInfo{
        Post post;
        String userName;

        public Post getPost() {
            return post;
        }

        public String getUserName() {
            return userName;
        }
    }

}
