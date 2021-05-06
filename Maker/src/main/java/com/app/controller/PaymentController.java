package com.app.controller;

import com.app.domain.ActiveUser;
import com.app.domain.User;
import com.app.service.AccountService;
import com.app.service.PaymentService;
import com.app.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;
    private final AccountService accountService;
    private ActiveUser activeUser;

    public PaymentController(PaymentService paymentService, UserService userService, AccountService accountService, ActiveUser activeUser) {
        this.paymentService = paymentService;
        this.userService = userService;
        this.accountService = accountService;
        this.activeUser = activeUser;
    }

    @GetMapping
    public String getHistoryOfPayment(@RequestParam(required = false) Boolean positive, Model model) {
        Long accountId = activeUser.getAccount().getId();
        model.addAttribute("payments", paymentService.getPaymentsByAccountId(accountId, positive));
        return "user/payment";
    }

    @GetMapping("/extraCash")
    public String giveExtraCash(HttpServletRequest request, @AuthenticationPrincipal User user) {
        accountService.putCash(BigDecimal.valueOf(100), user.getAccount());
        activeUser.saveSession(userService.updateUser(user));
        request.getSession().setAttribute("cash", activeUser.getAccount().getCash());
        ;
        return "redirect:/user";
    }


}
