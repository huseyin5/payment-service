package com.hsg.paymentservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaybackDto {

    private String paybackId;
    private String merchantPosId;
    private float paybackAmount;
    private float profitAmount;
    private LocalDateTime paybackDate;
}
