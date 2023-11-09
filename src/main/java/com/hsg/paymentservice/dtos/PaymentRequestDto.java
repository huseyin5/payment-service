package com.hsg.paymentservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

    private Long paymentId;

    @NotNull(message = "Payment amount is mandatory")
    private float paymentAmount;

    @NotNull(message = "Credit card number is mandatory")
    private int creditCardNo;

    private LocalDateTime paymentDate;

    @NotNull(message = "Merchant POS ID is mandatory")
    private String merchantPosId;

    private float totalReportedAmount;

}
