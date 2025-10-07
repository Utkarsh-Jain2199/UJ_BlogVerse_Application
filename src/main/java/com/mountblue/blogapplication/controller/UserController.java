package com.mountblue.blogapplication.controller;

import com.mountblue.blogapplication.entities.User;
import com.mountblue.blogapplication.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserDetailsManager userDetailsManager;

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
                                     @RequestParam String password,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {

        if (userRepository.findByEmail(email) != null) {
            return "register";
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword("{noop}" + password);
        user.setRole("author");
        userRepository.save(user);

        try {
            UserDetails userDetails = userDetailsManager.loadUserByUsername(email);
            
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    userDetails.getPassword(),
                    userDetails.getAuthorities()
            );
            
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);
            
            request.getSession(true).setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    securityContext
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
