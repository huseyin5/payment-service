package com.hsg.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {
    private float amount;
    private int creditCardNo;
    private String merchantId;
    private LocalDateTime paymentDate;
    private Boolean isReported;
}
