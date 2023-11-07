package com.hsg.paymentservice.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {

    private String paymentId;
    private float paymentAmount;
    private int creditCardNo;
    private LocalDateTime paymentDate;
    private Boolean isReported;
    private String merchantPosId;
    private int confirmationCode;
    private Boolean isPaybackStatus;
    private float totalReportedAmount;
}
