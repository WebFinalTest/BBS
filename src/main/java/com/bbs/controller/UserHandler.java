package com.bbs.controller;

import com.bbs.entity.User;
import com.bbs.service.ICommentService;
import com.bbs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/User")
@Controller
public class UserHandler {

    //跳转到用户界面
    @GetMapping("/view")
    public String showUserInfo() {
        return "/user/showUserInfo";
    }

//    @GetMapping("/create")
//    public String create(){
//        return "create";
//    }
//
//
//    @GetMapping("/index")
//    public String findAll(Model model){
//        model.addAttribute("users",userService.findAll());
//        model.addAttribute("comments",commentService.findByPostId(1L,1L));
//        return "index";
//    }
//
//    @GetMapping("/findById/{id}")
//    public ModelAndView findById(@PathVariable("id") Long id){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("update");
//        modelAndView.addObject("user",userService.findByUserId(id));
//        return modelAndView;
//    }
//
//    @PostMapping("/save")
//    public String save(User user){
//        try {
//            userService.register(user);
//        }catch (Exception e) {
//            System.out.println("Error");
//        }
//        return "redirect:/User/index";
//    }
//
//    @PostMapping("/update")
//    public String update(User user){
//        userService.update(user);
//        return "redirect:/User/index";
//    }

//    @GetMapping("/deleteById/{id}")
//    public String deleteById(@PathVariable("id") Long id){
//        userService.deleteById(id);
//        return "redirect:/User/index";
//    }

}
