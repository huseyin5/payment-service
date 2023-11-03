package com.hsg.paymentservice.controller;

import com.hsg.paymentservice.service.PaybackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payback")
@AllArgsConstructor
public class PaybackController {

    private final PaybackService paybackService;

    @GetMapping("/{merchantPosId}")
    public ResponseEntity<String> getPaybackReportByMerchantPosId(@PathVariable("merchantPosId") String merchantPosId) {
        return ResponseEntity.ok(paybackService.getPaybackReportByMerchantPosId(merchantPosId));
    }
}
