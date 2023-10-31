package com.hsg.paymentservice.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {

    private String approvalCode;

    private float amount;

    private int creditCardNo;

    private String merchantId;

    private LocalDateTime paymentDate;

    private Boolean isReported;
}
