package com.hsg.paymentservice.service;

import com.hsg.paymentservice.configuration.DtoConverterService;
import com.hsg.paymentservice.dto.PaymentRequestDto;
import com.hsg.paymentservice.dto.PaymentResponseDto;
import com.hsg.paymentservice.entity.Payment;
import com.hsg.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final DtoConverterService dtoConverterService;

    public PaymentResponseDto get(String id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return dtoConverterService.dtoClassConverter(payment, PaymentResponseDto.class);
    }

    public Payment save(@Valid PaymentRequestDto paymentRequestDto) {
        Payment payment = dtoConverterService.dtoClassConverter(paymentRequestDto, Payment.class);
        return paymentRepository.save(payment);
    }

    public List<PaymentResponseDto> findAll() {
        List<Payment> payments = paymentRepository.findAll();
        return dtoConverterService.dtoConverter(payments, PaymentResponseDto.class);
    }

    public Float getReport(){
        List<Payment> payments = paymentRepository.findByIsReported(false);
        float totalAmount = 0;
        for(Payment payment: payments)
            totalAmount += payment.getAmount();
        float finalTotalAmount = totalAmount;
        payments.forEach(payment -> payment.setIsReported(true));
        paymentRepository.saveAll(payments);
        return finalTotalAmount;
    }

    public Float getReportByMerchant(String merchantId){
        List<Payment> payments = paymentRepository.findByMerchantIdAndIsReported(merchantId, false);
        float totalAmount = 0;
        for(Payment payment: payments)
            totalAmount += payment.getAmount();
        float finalTotalAmount = totalAmount;
        payments.forEach(payment -> payment.setIsReported(true));
        paymentRepository.saveAll(payments);
        return finalTotalAmount;
    }

}
