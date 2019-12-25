package com.bbs.controller;

import com.bbs.entity.Post;
import com.bbs.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/Forum")
@Controller
public class ForumHandler {
    private IPostService postService;

    @Autowired
    public void setPostService(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping("/index")
    public String toIndex() {
        return "index";
    }

    @GetMapping("/test")
    public String test(){ return "test";}

    @GetMapping("/modelTest")
    @ResponseBody
    public Map modelTest(Model model){
        model.addAttribute("message","ok");
        return new HashMap();
    }

    @GetMapping("/mapTest")
    @ResponseBody
    public Map mapTest(){
        Map<String,Object> result = new HashMap<>();
        testPost testPost = new testPost();
        testPost.post = postService.findPosts().get(0);
        testPost.number = 2L;
        testPost.name = "哈哈";
        result.put("posts",postService.findPosts());
        result.put("message","ok");
        result.put("testPost",testPost);
        result.put("countPost",postService.countUnTopPosts());

        return result;
    }
    class testPost{
        Post post;
        Long number;
        String name;

        public Long getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        public Post getPost() {
            return post;
        }
    }

}
