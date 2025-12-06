//package com.schedular.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.schedular.model.User;
//import com.schedular.service.UserService;
//
//@Controller
//public class HomeController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";   // login.html
//    }
//
//    @GetMapping("/signup")
//    public String signupPage() {
//        return "signup";  // signup.html
//    }
//
//    @PostMapping("/save")
//    public String saveUser(User user) {
//        userService.SaveUser(user);
//        return "dash";   // dash.html
//    }
//    
//    @GetMapping("/dash")
//    public String getDash() {
//    	return "dash";
//    }
//}
