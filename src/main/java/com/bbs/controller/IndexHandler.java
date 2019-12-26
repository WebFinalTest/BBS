package com.bbs.controller;

import com.bbs.entity.Favorites;
import com.bbs.entity.User;
import com.bbs.service.IFavoritesService;
import com.bbs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/Index")
@Controller
public class IndexHandler {
    private IUserService userService;
//    private ICommentService commentService;
    private IFavoritesService favoritesService;

    @Autowired
    public void setFavoritesService(IFavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

//    @Autowired
//    public void setCommentService(ICommentService commentService) {
//        this.commentService = commentService;
//    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        List<Favorites> favoritesList = new ArrayList<>();
        try{
            User user = (User) session.getAttribute("user");
            favoritesList = favoritesService.findAllFavoritesByUserId(user.getUserId());
        }catch (Exception e) {
            System.out.println("ERROR:index");
        }
        model.addAttribute("favoritess",favoritesList);
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
        Map<String,String> result = new HashMap<>();
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
        Map<String,String> result = new HashMap<>();
        try{
            user = userService.register(user);
            if(user != null){
                result.put("message","success");
            }
            else
                result.put("message","fail");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message","error");
        }
        return result;
    }

    //检查邮箱是否被使用
    @PostMapping("/register/checkEmail")
    @ResponseBody
    public Map checkEmail(String email) {
        Map<String,String> result = new HashMap<>();
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

    //检查用户名是否被使用
    @PostMapping("/register/checkUserName")
    @ResponseBody
    public Map checkUserName(String userName) {
        Map<String,String> result = new HashMap<>();
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
