package com.hsg.paymentservice.service;

import com.hsg.paymentservice.entity.Payback;
import com.hsg.paymentservice.repository.PaybackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaybackService {

    private final PaybackRepository paybackRepository;

    public List<Payback> getAllPayback() {
        return paybackRepository.findAll();
    }
}
