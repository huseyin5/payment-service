package com.hsg.paymentservice.service;

import com.hsg.paymentservice.dtos.MerchantPosResponseDto;
import com.hsg.paymentservice.dtos.PaybackDto;
import com.hsg.paymentservice.entity.MerchantPos;
import com.hsg.paymentservice.entity.Payback;
import com.hsg.paymentservice.entity.Payment;
import com.hsg.paymentservice.repository.MerchantPosRepository;
import com.hsg.paymentservice.repository.PaybackRepository;
import com.hsg.paymentservice.repository.PaymentRepository;
import com.hsg.paymentservice.utilities.DataResult;
import com.hsg.paymentservice.utilities.SuccessDataResult;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        float totalCommission = 0;
        float commissionRatio = 2.29f;

        for (Payment payment : payments) {
            totalPaybackAmount += payment.getTotalReportedAmount() - payment.getTotalReportedAmount() * commissionRatio / 100;
            totalCommission = payment.getTotalReportedAmount() * commissionRatio / 100;
        }

        float finalProfit = totalCommission;
        float finalPaybackAmount = totalPaybackAmount;
        payments.forEach(payment -> {
            payment.setIsPaybackStatus(true);
            paymentRepository.save(payment);
        });

        Payback payback = new Payback();
        payback.setPaybackAmount(finalPaybackAmount);
        payback.setMerchantPos(merchantPosRepository.findByMerchantPosId(merchantPosId));
        payback.setProfitAmount(finalProfit);
        paybackRepository.save(payback);

        return "Merchant pos id: " + merchantPosId + " payback amount: " + finalPaybackAmount + " TL";
    }

    public String getTotalProfitAmount() {
        List<Payback> paybacks = paybackRepository.findAll();
        float totalProfitAmount = 0;

        for (Payback payback : paybacks) {
            totalProfitAmount += payback.getProfitAmount();
        }

        return "Total profit amount: " + totalProfitAmount + " TL";
    }
}
