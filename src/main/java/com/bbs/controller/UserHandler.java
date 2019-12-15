package com.bbs.controller;

import com.bbs.entity.User;
import com.bbs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/User")
@Controller
public class UserHandler {

    private IUserService userService;

    @Autowired
    public UserHandler(IUserService userService) {
        this.userService = userService;
    }


    @GetMapping("/create")
    public String create(){
        return "create";
    }

    @GetMapping("/index")
    public String findAll(Model model){
        model.addAttribute("users",userService.findAll());
        return "index";
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update");
        modelAndView.addObject("user",userService.findById(id));
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(User user){
        try {
            userService.save(user);
        }catch (Exception e) {
            System.out.println("Error");
        }
        return "redirect:/User/index";
    }

    @PostMapping("/update")
    public String update(User user){
        userService.update(user);
        return "redirect:/User/index";
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/User/index";
    }

}
