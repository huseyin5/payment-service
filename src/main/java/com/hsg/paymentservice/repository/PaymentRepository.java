package com.hsg.paymentservice.repository;

import com.hsg.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findByIsReported(boolean isReported);

    List<Payment> findByMerchantIdAndIsReported(String merchantId, boolean isReported);
}
