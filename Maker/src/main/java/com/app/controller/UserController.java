package com.app.controller;

import com.app.domain.ActiveUser;
import com.app.domain.User;
import com.app.exception.UserServiceException;
import com.app.service.PaymentService;
import com.app.service.PictureService;
import com.app.service.TicketService;
import com.app.service.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final TicketService ticketService;
    private final PaymentService paymentService;
    private final PictureService pictureService;
    private ActiveUser activeUser;

    public UserController(UserService userService, TicketService ticketService, PaymentService paymentService, PictureService pictureService, ActiveUser activeUser) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.paymentService = paymentService;
        this.pictureService = pictureService;
        this.activeUser = activeUser;
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

    @GetMapping("/uploadedPicture")
    public void getUploadedPicture(HttpServletResponse response) throws IOException {
        File picture = new File(activeUser.getPicturePath());
        response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(picture.getName()));
        IOUtils.copy(new FileInputStream(picture), response.getOutputStream());
    }


    @GetMapping
    public String userView(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("pic", pictureService.getAnonymousPicture());
        model.addAttribute("user", user);
        model.addAttribute("ticketNumber", ticketService.countAllByUserId(activeUser.getUserId()));
        model.addAttribute("positiveSum", paymentService.sumAllByPositiveAccountId(activeUser.getAccount().getId()));
        return "user/edit";
    }

    @PostMapping("/picture")
    public String updateUser(MultipartFile file, @AuthenticationPrincipal User user) throws IOException {
        String picturePath = pictureService.saveFileAndGetURL(file);
        user.setPicturePath(picturePath);
        activeUser.saveSession(userService.updateUser(user));
        activeUser.saveSession(user);
        return "redirect:/user";
    }

    @ResponseBody
    @GetMapping("/exists")
    public Boolean checkIfUsernameExists(@RequestParam String username){
        return userService.existByUsername(username);
    }

}
