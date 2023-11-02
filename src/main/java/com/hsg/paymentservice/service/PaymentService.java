package com.hsg.paymentservice.service;

import com.hsg.paymentservice.configuration.DtoConverterService;
import com.hsg.paymentservice.dto.PaymentRequestDto;
import com.hsg.paymentservice.dto.PaymentResponseDto;
import com.hsg.paymentservice.entity.Payment;
import com.hsg.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final DtoConverterService dtoConverterService;

    public PaymentResponseDto getPaymentDetail(String id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return dtoConverterService.dtoClassConverter(payment, PaymentResponseDto.class);
    }

    public Payment savePayment(PaymentRequestDto paymentRequestDto) {
        Payment payment = dtoConverterService.dtoClassConverter(paymentRequestDto, Payment.class);
        if(paymentRepository.existsByConfirmationCode(payment.getConfirmationCode()))
            throw new IllegalArgumentException("Confirmation code is already used");
        return paymentRepository.save(payment);
    }

    public Boolean existsConfirmationCode(int confirmationCode) {
        return paymentRepository.existsByConfirmationCode(confirmationCode);
    }

    public List<PaymentResponseDto> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return dtoConverterService.dtoConverter(payments, PaymentResponseDto.class);
    }

    public String getReport(){
        List<Payment> payments = paymentRepository.findByIsReported(false);
        float totalAmount = 0;
        for(Payment payment: payments)
            totalAmount += payment.getAmount();
        float finalTotalAmount = totalAmount;
        payments.forEach(payment -> payment.setIsReported(true));
        paymentRepository.saveAll(payments);
        return "Total amount is " + finalTotalAmount + " TL";
    }

//    public String getReportByMerchant(String merchantId){
//        List<Payment> payments = paymentRepository.findByMerchantIdAndIsReported(merchantId, false);
//        float totalAmount = 0;
//        for(Payment payment: payments)
//            totalAmount += payment.getAmount();
//        float finalTotalAmount = totalAmount;
//        payments.forEach(payment -> payment.setIsReported(true));
//        paymentRepository.saveAll(payments);
//        return "Total amount for merchant " + merchantId + " is " + finalTotalAmount + " TL";
//    }

    public String getReportByMerchantPosId(String merchantPosId){
        List<Payment> payments = paymentRepository.findByMerchantPosIdAndIsReported(merchantPosId, false);
        float totalAmount = 0;
        for(Payment payment: payments)
            totalAmount += payment.getAmount();
        float finalTotalAmount = totalAmount;
        payments.forEach(payment -> payment.setIsReported(true));
        paymentRepository.saveAll(payments);
        return "Total amount for merchantPosId " + merchantPosId + " is " + finalTotalAmount + " TL";
    }
}
