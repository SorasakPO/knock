package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.SignupService;
import com.example.demo.model.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    @Autowired
    private SignupService signupService;

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup"; // return หน้าฟอร์ม signup.html
    }

    @PostMapping("/signup")
    public String signupUser(@ModelAttribute SignupRequest user, Model model) {
        if (signupService.isUsernameAvailable(user.getUsername())) {
            signupService.createUser(user);
            model.addAttribute("signupSuccess", true);
        }
        else {
            model.addAttribute("signupError", "Username not available");
        }
        // return หน้าฟอร์ม signup.html เช่นกัน แต่จะมี message ปรากฎ
        return "signup";
    }

}