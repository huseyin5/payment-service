package com.hsg.paymentservice.service;

import com.hsg.paymentservice.entity.Payment;
import com.hsg.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaybackService {

    private final PaymentRepository paymentRepository;

    public String getPaybackReportByMerchantPosId(String merchantPosId) {
        List<Payment> payments = paymentRepository.findAllByMerchantPosIdAndIsPaybackStatus(merchantPosId, false);

        float totalPaybackAmount = 0;
        float commissionRate = 2.29f;

        for (Payment payment : payments) {
            totalPaybackAmount = payment.getTotalReportedAmount()- payment.getTotalReportedAmount() * commissionRate / 100;
        }

        float finalTotalPaybackAmount = totalPaybackAmount;
        payments.forEach(payment -> payment.setIsPaybackStatus(true));
        payments.forEach(payment -> payment.setTotalPaybackAmount(finalTotalPaybackAmount));

        paymentRepository.saveAll(payments);

        return "Merchant Pos Id: " + merchantPosId + " Total payback amount is " + finalTotalPaybackAmount + " TL";
    }
}
