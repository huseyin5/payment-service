package com.hsg.paymentservice.service;

import com.hsg.paymentservice.configuration.DtoConverterService;
import com.hsg.paymentservice.dto.PaymentDto;
import com.hsg.paymentservice.entity.Payment;
import com.hsg.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final DtoConverterService dtoConverterService;
    private final ModelMapper modelMapper;

    public PaymentDto get(String id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        return modelMapper.map(payment, PaymentDto.class);
    }

//     public Payment save(PaymentDto paymentDto) {
//        Payment payment = dtoConverterService.dtoClassConverter(paymentDto, Payment.class);
//        return paymentRepository.save(payment);
//    }

    public Payment save(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setAmount(paymentDto.getAmount());
        payment.setCreditCardNo(paymentDto.getCreditCardNo());
        payment.setMerchantId(paymentDto.getMerchantId());
        payment.setApprovalCode(paymentDto.getApprovalCode());
        payment.setPaymentDate(paymentDto.getPaymentDate());
        return paymentRepository.save(payment);
    }

    public Slice<PaymentDto> findAll(Pageable pageable) {
        Slice<Payment> payments = paymentRepository.findAll(pageable);
        return null;
    }
}
