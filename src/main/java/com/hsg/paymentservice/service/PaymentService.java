package com.hsg.paymentservice.service;

import com.hsg.paymentservice.dtos.PaymentRequestDto;
import com.hsg.paymentservice.dtos.PaymentResponseDto;
import com.hsg.paymentservice.entity.MerchantPos;
import com.hsg.paymentservice.entity.Payment;
import com.hsg.paymentservice.repository.MerchantPosRepository;
import com.hsg.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final MerchantPosRepository merchantPosRepository;
    private final ModelMapper modelMapper;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Transactional
    public PaymentRequestDto savePayment(PaymentRequestDto paymentRequestDto) {
        Payment payment = modelMapper.map(paymentRequestDto, Payment.class);

        if (paymentRepository.existsByConfirmationCode(payment.getConfirmationCode())) {
            throw new IllegalArgumentException("Confirmation code is already used");
        }

        payment = paymentRepository.save(payment);
        paymentRequestDto.setPaymentId(payment.getPaymentId());
        paymentRequestDto.setPaymentDate(payment.getPaymentDate());
        return paymentRequestDto;
    }

    public PaymentResponseDto getPaymentDetail(String id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));

        return modelMapper.map(payment, PaymentResponseDto.class);
    }

    public Boolean existsConfirmationCode(String confirmationCode) {
        return paymentRepository.existsByConfirmationCode(confirmationCode);
    }

    public String getReportByMerchantPosId(String merchantPosId) {

        MerchantPos merchantPosIdIndex = merchantPosRepository.findByMerchantPosId(merchantPosId);
        List<Payment> payments = paymentRepository.findByMerchantPosAndIsReported(merchantPosIdIndex, false);

        float totalAmount = 0;
        for (Payment payment : payments) {
            totalAmount += payment.getPaymentAmount();
        }

        float finalTotalAmount = totalAmount;

        payments.forEach(payment -> payment.setIsReported(true));
        payments.forEach(payment -> payment.setTotalReportedAmount(payment.getTotalReportedAmount() + finalTotalAmount));
        paymentRepository.saveAll(payments);

        return "Total amount for merchantPosId " + merchantPosId + " is " + finalTotalAmount + " TL";
    }

}
