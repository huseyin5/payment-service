package com.hsg.paymentservice.repository;

import com.hsg.paymentservice.entity.MerchantPos;
import com.hsg.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

    List<Payment> findByMerchantPosAndIsReported(MerchantPos merchantPos, Boolean isReported);

    Boolean existsByConfirmationCode(String confirmationCode);

    List<Payment> findAllByMerchantPos_MerchantPosIdAndIsPaybackStatusFalse(String merchantPos);
}
