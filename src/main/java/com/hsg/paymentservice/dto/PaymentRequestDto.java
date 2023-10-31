package com.hsg.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

    @NotBlank(message = "Amount can not be null")
    @Size(min = 1, max = 10, message = "Amount must be between 1 and 10 characters")
    private float amount;

    @NotBlank(message = "Credit Card can not be null")
    @Size(min = 4, max = 4, message = "Credit Card must be 4 characters")
    private int creditCardNo;

    @NotBlank(message = "Credit Card can not be null")
    @Size(min = 5, max = 5, message = "Merchant Id must be 5 characters")
    private String merchantId;

    private LocalDateTime paymentDate;
}
