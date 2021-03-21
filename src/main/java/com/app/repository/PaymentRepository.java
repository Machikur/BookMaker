package com.app.repository;

import com.app.domain.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment,Long> {

    List<Payment> findAllByAccountIdOrderByPayTimeDesc(Long accountId);

    List<Payment> findAllByAccountIdAndPositiveOrderByPayTimeDesc(Long accountId, Boolean positive);
}
