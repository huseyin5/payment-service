package com.hsg.paymentservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

    private String paymentId;
    private float paymentAmount;
    private int creditCardNo;
    private LocalDateTime paymentDate;
    private String merchantPosId;
    private int confirmationCode;
    private float totalReportedAmount;
}
