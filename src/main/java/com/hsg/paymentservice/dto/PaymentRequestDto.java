package com.hsg.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

    private float amount;
    private int creditCardNo;
    private String merchantId;
    private LocalDateTime paymentDate;
    private String merchantPosId;
    private int confirmationCode;
    private float totalReportedAmount;
}
