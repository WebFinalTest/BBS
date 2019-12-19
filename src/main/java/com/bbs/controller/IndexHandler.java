package com.bbs.controller;

import com.bbs.entity.User;
import com.bbs.service.ICommentService;
import com.bbs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/Index")
@Controller
public class IndexHandler {
    private IUserService userService;
    private ICommentService commentService;

    @Autowired
    public void setCommentService(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/login/do")
    @ResponseBody
    public Map doLogin(User user, HttpSession session) {
        Map<String,String> result = new HashMap<String, String>();
        try{
            user = userService.login(user.getEmail(),user.getPassword());
            if(user != null){
                user.setPassword("");
                session.setAttribute("user",user);
                result.put("message","success");
            }
            else
                result.put("message","fail");
        } catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    @PostMapping("/register/do")
    @ResponseBody
    public Map doRegister(User user) {
        Map<String,String> result = new HashMap<String, String>();
        try{
            user = userService.register(user);
            if(user != null){
                result.put("message","success");
            }
            else
                result.put("message","fail");
        } catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    @PostMapping("/register/checkEmail")
    @ResponseBody
    public Map checkEmail(String email) {
        Map<String,String> result = new HashMap<String, String>();
        try{
            if(userService.isUsedByEmail(email))
                result.put("message","isUsed");
            else
                result.put("message","isNotUsed");
        } catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }

    @PostMapping("/register/checkUserName")
    @ResponseBody
    public Map checkUserName(String userName) {
        Map<String,String> result = new HashMap<String, String>();
        try{
            if(userService.isUsedByUserName(userName))
                result.put("message","isUsed");
            else
                result.put("message","isNotUsed");
        } catch (Exception e) {
            result.put("message","error");
        }
        return result;
    }
}
