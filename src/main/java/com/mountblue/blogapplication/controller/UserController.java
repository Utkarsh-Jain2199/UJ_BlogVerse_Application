package com.mountblue.blogapplication.controller;

import com.mountblue.blogapplication.entities.User;
import com.mountblue.blogapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/loginPage")
    public String showMyLoginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public String registerUser() {
        return "register";
    }

    @RequestMapping("/addUser")
    public String saveRegisteredUser(@RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam String password) {

        if (userRepository.findByEmail(email) != null) {
            return "register";
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword("{noop}" + password);
        user.setRole("author");
        userRepository.save(user);

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("author");
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                email,
                password,
                Collections.singletonList(authority)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }
}
