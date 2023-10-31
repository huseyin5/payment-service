package com.hsg.paymentservice.controller;

import com.hsg.paymentservice.dto.PaymentDto;
import com.hsg.paymentservice.entity.Payment;
import com.hsg.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(paymentService.get(id));
    }

    @PostMapping
    public ResponseEntity<Payment> save(@Validated @RequestBody PaymentDto paymentDto) {
        return new ResponseEntity<>(paymentService.save(paymentDto), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<Slice<PaymentDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(paymentService.findAll(pageable));
    }

}
