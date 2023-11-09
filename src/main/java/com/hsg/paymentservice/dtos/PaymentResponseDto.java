package com.hsg.paymentservice.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {

    private Long paymentId;
    private float paymentAmount;
    private int creditCardNo;
    private LocalDateTime paymentDate;
    private Boolean isReported;
    private String merchantPosId;
    private String confirmationCode;
    private Boolean isPaybackStatus;
    private float totalReportedAmount;
}
