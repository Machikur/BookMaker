package com.app.service;

import com.app.domain.Payment;
import com.app.repository.PaymentRepository;
import com.sun.istack.Nullable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getPaymentsByAccountId(Long accountId, @Nullable Boolean positive) {
        if (positive == null) {
            return paymentRepository.findAllByAccountIdOrderByPayTimeDesc(accountId);
        }
        return paymentRepository.findAllByAccountIdAndPositiveOrderByPayTimeDesc(accountId, positive);
    }

    public Double sumAllByPositiveAccountId(Long accountId) {
        Double result = paymentRepository.sumAllById(accountId);
        return result != null ? Math.abs(result) : 0d;
    }

}
