package com.hsg.paymentservice.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantPosRequestDto {

    @NotNull(message = "Merchant POS ID is mandatory")
    private String merchantPosId;

    @NotNull(message = "Merchant name is mandatory")
    private String merchantName;
}
