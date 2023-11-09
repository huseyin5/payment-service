package com.hsg.paymentservice.controller;

import com.hsg.paymentservice.dtos.MerchantPosRequestDto;
import com.hsg.paymentservice.dtos.MerchantPosResponseDto;
import com.hsg.paymentservice.entity.MerchantPos;
import com.hsg.paymentservice.service.MerchantPosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchantpos")
@AllArgsConstructor
public class MerchantPosController {

    private final MerchantPosService merchantPosService;

    @GetMapping
    public ResponseEntity<List<MerchantPos>> getAllMerchantPos() {
        return ResponseEntity.ok(merchantPosService.getAllMerchantPos());
    }

    @PostMapping
    public ResponseEntity<MerchantPosRequestDto> saveMerchantPos(@Validated @RequestBody MerchantPosRequestDto merchantPosRequestDto) {
        return ResponseEntity.ok(merchantPosService.saveMerchantPos(merchantPosRequestDto));
    }

    @GetMapping("/{merchantPosId}")
    public ResponseEntity<MerchantPosResponseDto> getMerchantDetail(@PathVariable("merchantPosId") String merchantPosId) {
        return ResponseEntity.ok(merchantPosService.getMerchantDetail(merchantPosId));
    }

    @GetMapping("/report/{merchantPosId}")
    public ResponseEntity<String> getReportByMerchantPosId(@PathVariable("merchantPosId") String merchantPosId) {
        return ResponseEntity.ok(merchantPosService.getMerchantPosIdTotalPaybackAmount(merchantPosId));
    }
}
