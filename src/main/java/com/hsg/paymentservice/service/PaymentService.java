package com.hsg.paymentservice.service;

import com.hsg.paymentservice.configuration.DtoConverterService;
import com.hsg.paymentservice.dto.PaymentRequestDto;
import com.hsg.paymentservice.dto.PaymentResponseDto;
import com.hsg.paymentservice.entity.Payment;
import com.hsg.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final DtoConverterService dtoConverterService;
    private final ModelMapper modelMapper;

    public PaymentResponseDto get(String id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return dtoConverterService.dtoClassConverter(payment, PaymentResponseDto.class);
    }

    public Payment save(PaymentRequestDto paymentRequestDto) {
        Payment payment = dtoConverterService.dtoClassConverter(paymentRequestDto, Payment.class);
        return paymentRepository.save(payment);
    }

//    public Payment save(PaymentDto paymentDto) {
//        Payment payment = new Payment();
//        payment.setAmount(paymentDto.getAmount());
//        payment.setCreditCardNo(paymentDto.getCreditCardNo());
//        payment.setMerchantId(paymentDto.getMerchantId());
//        payment.setApprovalCode(paymentDto.getApprovalCode());
//        payment.setPaymentDate(paymentDto.getPaymentDate());
//        return paymentRepository.save(payment);
//    }

    public List<PaymentResponseDto> findAll() {
        List<Payment> payments = paymentRepository.findAll();
        return dtoConverterService.dtoConverter(payments, PaymentResponseDto.class);
    }
}
