package com.app.repository;

import com.app.domain.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {

    List<Payment> findAllByAccountIdOrderByPayTimeDesc(Long accountId);

    List<Payment> findAllByAccountIdAndPositiveOrderByPayTimeDesc(Long accountId, Boolean positive);

    @Query("SELECT SUM(P.amount) FROM Payment P WHERE P.account.id = :Id AND P.positive = true")
    Double sumAllById(@Param("Id") Long accountId);
}
