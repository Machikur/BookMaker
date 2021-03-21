package com.app.controller;

import com.app.domain.User;
import com.app.exception.UserServiceException;
import com.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result,
                           Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("error", result.getAllErrors().get(0).toString());
            return "user/register";
        }
        try {
            userService.saveNewUser(user);
        } catch (UserServiceException ex) {
            model.addAttribute("error", ex.getMessage());
            return "user/register";
        }
        attributes.addFlashAttribute("message", "Konto zostało utworzone");
        return "redirect:/";
    }

}
