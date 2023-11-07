package com.hsg.paymentservice.controller;

import com.hsg.paymentservice.dtos.PaymentRequestDto;
import com.hsg.paymentservice.dtos.PaymentResponseDto;
import com.hsg.paymentservice.entity.Payment;
import com.hsg.paymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
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

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @PostMapping
    public ResponseEntity<?> savePayment(@Validated @RequestBody PaymentRequestDto paymentRequestDto) {
        try {
            PaymentRequestDto payment = paymentService.savePayment(paymentRequestDto);
            return new ResponseEntity<>(payment, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDto> getPaymentDetail(@PathVariable("paymentId") String paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentDetail(paymentId));
    }

    @GetMapping("/report/{merchantPosId}")
    public ResponseEntity<String> getReportByMerchantPosId(@PathVariable("merchantPosId") String merchantPosId) {
        return ResponseEntity.ok(paymentService.getReportByMerchantPosId(merchantPosId));
    }

}
