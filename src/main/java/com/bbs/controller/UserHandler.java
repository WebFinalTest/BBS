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
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    //跳转到用户界面
    @GetMapping("/view")
    public String showUserInfo(Model model,HttpSession session) {
        try{
            User user = (User)session.getAttribute("user");
            model.addAttribute("uer",user);
        }catch (Exception e) {
            System.out.println("ERROR:View");
        }
        return "user/showUserInfo";
    }

    //注销
    @GetMapping("/down")
    public Map down(HttpSession session) {
        Map map = new HashMap();
        try {
            session.removeAttribute("user");
            map.put("message","success");
        }catch (Exception e) {
            map.put("message","error");
        }
        return map;
    }


    //修改用户信息提交
    @PostMapping("/updateUserInfo/do")
    @ResponseBody
    public Map doUpdateUserInfo(HttpSession session,User user) {
        Map map = new HashMap();
        try {
            User user1 = (User)session.getAttribute("user");
            user.setUserId(user1.getUserId());
            userService.update(user);
            map.put("message","success");
        }catch (Exception e) {
            map.put("message","error");
        }
        return map;
    }

//    //修改用户密码
//    @PostMapping("/updatePassword")
//    public Map<String,String> updatePassword(HttpSession session,String originalPassword,String newPassword) {
//        Map map = new HashMap();
//        try {
//            User user = (User) session.getAttribute("user");
//            if(userService.updatePasswordByUserId(user.getUserId(),originalPassword,newPassword))
//                map.put("message","success");
//            else
//                map.put("message","fail");
//        }catch (Exception e) {
//            map.put("message","error");
//        }
//        return map;
//    }

    //    //修改用户信息界面
//    @GetMapping("/updateUserInfo")
//    public String updateUserInfo(Model model,HttpSession session){
//        try{
//            User user = (User)session.getAttribute("user");
//            model.addAttribute("uer",user);
//        }catch (Exception e) {
//            System.out.println("ERROR:updateUserInfo");
//        }
//        return "/user/updateUserInfo";
//    }

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
