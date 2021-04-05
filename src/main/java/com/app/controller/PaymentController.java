package com.app.controller;

import com.app.domain.ActiveUser;
import com.app.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private ActiveUser activeUser;

    public PaymentController(PaymentService paymentService, ActiveUser activeUser) {
        this.paymentService = paymentService;
        this.activeUser = activeUser;
    }

    @GetMapping
    public String getHistoryOfPayment(@RequestParam(required = false) Boolean positive, Model model) {
        Long accountId = activeUser.getAccount().getId();
        model.addAttribute("payments", paymentService.getPaymentsByAccountId(accountId, positive));
        return "user/payment";
    }

}
