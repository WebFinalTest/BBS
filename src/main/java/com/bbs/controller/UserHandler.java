package com.bbs.controller;

import com.bbs.entity.User;
import com.bbs.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/User")
@Controller
public class UserHandler {

    @Autowired
    private IUserRepository userRepository;



    @GetMapping("/create")
    public String create(){
        return "create";
    }

    @GetMapping("/index")
    public String findAll(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "index";
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update");
        modelAndView.addObject("user",userRepository.findById(id));
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(User user){
        userRepository.save(user);
        return "redirect:/User/index";
    }

    @PostMapping("/update")
    public String update(User user){
        userRepository.update(user);
        return "redirect:/User/index";
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return "index";
    }

}
