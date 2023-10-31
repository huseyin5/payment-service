package com.hsg.paymentservice.controller;

import com.hsg.paymentservice.dto.PaymentRequestDto;
import com.hsg.paymentservice.dto.PaymentResponseDto;
import com.hsg.paymentservice.entity.Payment;
import com.hsg.paymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(paymentService.get(id));
    }

    @PostMapping
    public ResponseEntity<Payment> save(@Valid @RequestBody PaymentRequestDto paymentRequestDto) {
        return new ResponseEntity<>(paymentService.save(paymentRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getAll() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/report")
    public ResponseEntity<Float> getReport() {
        return ResponseEntity.ok(paymentService.getReport());
    }

}
