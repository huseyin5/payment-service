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

    @NotNull(message = "Amount cannot be null")
    private float amount;

    @NotNull(message = "Credit card no cannot be null")
    private int creditCardNo;

    @NotNull(message = "Currency cannot be blank")
    private String merchantId;

    private LocalDateTime paymentDate;
}
