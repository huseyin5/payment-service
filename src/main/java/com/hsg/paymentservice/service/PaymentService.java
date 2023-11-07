package com.hsg.paymentservice.service;

import com.hsg.paymentservice.configuration.DtoConverterService;
import com.hsg.paymentservice.dtos.PaymentRequestDto;
import com.hsg.paymentservice.dtos.PaymentResponseDto;
import com.hsg.paymentservice.entity.MerchantPos;
import com.hsg.paymentservice.entity.Payment;
import com.hsg.paymentservice.repository.MerchantPosRepository;
import com.hsg.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    public PaymentRequestDto savePayment(PaymentRequestDto paymentRequestDto) {

        Payment payment = modelMapper.map(paymentRequestDto, Payment.class);

        if (paymentRepository.existsByConfirmationCode(payment.getConfirmationCode())) {
            throw new IllegalArgumentException("Confirmation code is already used");
        }

        if (paymentRepository.existsByPaymentId(payment.getPaymentId())) {
            throw new IllegalArgumentException("Payment id is already used");
        }

        payment = paymentRepository.save(payment);
        paymentRequestDto.setPaymentId(payment.getPaymentId());
        return paymentRequestDto;
    }

    public PaymentResponseDto getPaymentDetail(String id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));

        return modelMapper.map(payment, PaymentResponseDto.class);
    }

    public Boolean existsConfirmationCode(int confirmationCode) {
        return paymentRepository.existsByConfirmationCode(confirmationCode);
    }

    public Boolean existsPaymentId(String paymentId) {
        return paymentRepository.existsByPaymentId(paymentId);
    }




    public String getReportByMerchantPosId(String merchantPosId) {
        List<Payment> payments = paymentRepository.findByMerchantPosAndIsReported(merchantPosRepository.findByMerchantPosId(merchantPosId), false);

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

//    public String getReport() {
//        List<Payment> payments = paymentRepository.findByIsReported(false);
//        List<MerchantPos> merchantPosList = merchantPosRepository.findAll();
//
//        float totalAmount = 0;
//        for (Payment payment : payments) {
//            totalAmount += payment.getPaymentAmount();
//        }
//
//        float finalTotalAmount = totalAmount;
//        payments.forEach(payment -> payment.setTotalReportedAmount(finalTotalAmount + payment.getTotalReportedAmount()));
//        payments.forEach(payment -> payment.setIsReported(true));
//        paymentRepository.saveAll(payments);
//
//        return "Total amount is " + finalTotalAmount + " TL";
//    }
}
