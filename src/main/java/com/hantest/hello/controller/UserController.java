package com.hantest.hello.controller;

import ch.qos.logback.core.model.Model;
import com.hantest.hello.entity.User;
import com.hantest.hello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user != null) {
            return "redirect:/home";
        } else {
            model.addText("error");
            return "login";
        }
    }
}
