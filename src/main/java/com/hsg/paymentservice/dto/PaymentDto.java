package com.hsg.paymentservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private String approvalCode;
    private float amount;
    private int creditCardNo;
    private String merchantId;
    private String paymentDate;
    private Boolean isReported;
}
