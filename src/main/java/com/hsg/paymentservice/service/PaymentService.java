package com.hsg.paymentservice.service;

import com.hsg.paymentservice.dto.PaymentDto;
import com.hsg.paymentservice.entity.Payment;
import com.hsg.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    public PaymentDto get(String id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        return modelMapper.map(payment, PaymentDto.class);
    }

    @Transactional
    public PaymentDto save(PaymentDto paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        payment = paymentRepository.save(payment);
        paymentDto.setApprovalCode(payment.getApprovalCode());
        return paymentDto;
    }

    public Slice<PaymentDto> findAll(Pageable pageable) {
        Slice<Payment> payments = paymentRepository.findAll(pageable);
        return null;
    }
}
