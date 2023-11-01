package com.hsg.paymentservice.controller;

import com.hsg.paymentservice.dto.PaymentRequestDto;
import com.hsg.paymentservice.dto.PaymentResponseDto;
import com.hsg.paymentservice.entity.Payment;
import com.hsg.paymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> getPaymentDetail(@PathVariable("id") String id) {
        return ResponseEntity.ok(paymentService.getpaymentDeatail(id));
    }

    @PostMapping
    public ResponseEntity<Payment> savePayment(@Validated @RequestBody PaymentRequestDto paymentRequestDto) {
        return new ResponseEntity<>(paymentService.savePayment(paymentRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/report")
    public ResponseEntity<String> getReport() {
        return ResponseEntity.ok(paymentService.getReport());
    }

    @GetMapping("/report/{merchantId}")
    public ResponseEntity<String> getReportByMerchant(@PathVariable("merchantId") String merchantId) {
        return ResponseEntity.ok(paymentService.getReportByMerchant(merchantId));
    }

}
