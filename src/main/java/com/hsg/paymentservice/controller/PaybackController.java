package com.hsg.paymentservice.controller;

import com.hsg.paymentservice.entity.Payback;
import com.hsg.paymentservice.service.PaybackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
