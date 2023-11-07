package com.hsg.paymentservice.controller;

import com.hsg.paymentservice.entity.Payback;
import com.hsg.paymentservice.service.PaybackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payback")
@AllArgsConstructor
public class PaybackController {

    private final PaybackService paybackService;

    @GetMapping
    public ResponseEntity<List<Payback>> getAllPayback() {
        return ResponseEntity.ok(paybackService.getAllPayback());
    }

    @GetMapping("/{merchantPosId}")
    public ResponseEntity<String> getPaybackMerchantPosId(@PathVariable("merchantPosId") String merchantPosId) {
        return ResponseEntity.ok(paybackService.getPaybackMerchantPosId(merchantPosId));
    }

    @GetMapping("/profit")
    public ResponseEntity<String> getTotalProfitAmount() {
        return ResponseEntity.ok(paybackService.getTotalProfitAmount());
    }
}
