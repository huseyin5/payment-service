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
        return ResponseEntity.ok(paymentService.getPaymentDetail(id));
    }

    @PostMapping
    public ResponseEntity<?> savePayment(@Validated @RequestBody PaymentRequestDto paymentRequestDto) {
        try {
            Payment payment = paymentService.savePayment(paymentRequestDto);
            return new ResponseEntity<>(payment, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/report")
    public ResponseEntity<String> getReport() {
        return ResponseEntity.ok(paymentService.getReport());
    }

//    @GetMapping("/report/{merchantId}")
//    public ResponseEntity<String> getReportByMerchant(@PathVariable("merchantId") String merchantId) {
//        return ResponseEntity.ok(paymentService.getReportByMerchant(merchantId));
//    }

    @GetMapping("/report/{merchantPosId}")
    public ResponseEntity<String> getReportByMerchantPosId(@PathVariable("merchantPosId") String merchantPosId) {
        return ResponseEntity.ok(paymentService.getReportByMerchantPosId(merchantPosId));
    }
}
