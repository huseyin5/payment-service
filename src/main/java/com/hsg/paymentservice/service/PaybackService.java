package com.hsg.paymentservice.service;

import com.hsg.paymentservice.entity.MerchantPos;
import com.hsg.paymentservice.entity.Payback;
import com.hsg.paymentservice.entity.Payment;
import com.hsg.paymentservice.repository.MerchantPosRepository;
import com.hsg.paymentservice.repository.PaybackRepository;
import com.hsg.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaybackService {

    private final PaybackRepository paybackRepository;
    private final PaymentRepository paymentRepository;
    private final MerchantPosRepository merchantPosRepository;

    public List<Payback> getAllPayback() {
        return paybackRepository.findAll();
    }

    public String getPaybackMerchantPosId(String merchantPosId) {

        List<Payment> payments = paymentRepository.findAllByMerchantPos_MerchantPosIdAndIsPaybackStatusFalse(merchantPosId);

        float totalPaybackAmount = 0;
        float commissionAmount = 2.29f;

        for (Payment payment : payments) {
            totalPaybackAmount += payment.getTotalReportedAmount() * commissionAmount / 100;

        }

        float finalPaybackAmount = totalPaybackAmount;
        payments.forEach(payment -> {
            payment.setIsPaybackStatus(true);
            paymentRepository.save(payment);
        });

        Payback payback = new Payback();
        payback.setPaybackAmount(finalPaybackAmount);
        paybackRepository.save(payback);

        return "Merchant pos id: " + merchantPosId + " payback amount: " + finalPaybackAmount + " TL";
    }
}
