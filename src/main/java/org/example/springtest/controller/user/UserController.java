package org.example.springtest.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springtest.domain.user.User;
import org.example.springtest.security.service.CustomUserDetailsService;
import org.example.springtest.service.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/login-success")
    public String loginSuccess(Model model, Principal principal, Authentication authentication) { //spring security에서 처리된 정보를 받을때 -> principal (디테일한건 Authentication)
        log.info("===============> user principal : {}", principal );
        log.info("===============> user auth : {}", authentication);

        model.addAttribute("user", principal.getName());
        model.addAttribute("auth", authentication.getAuthorities());

        return "user/login-success";
    }

    @GetMapping("/login-failure")
    public String loginFailure() {
        return "user/login-failure";
    }

    @GetMapping("/register")
    public String register() {
        return "user/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody User user) {
        userService.save(user);
        return "success";
    }
}
