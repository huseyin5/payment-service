package com.hsg.paymentservice.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantPosRequestDto {

    private String merchantPosId;
    private String merchantName;
}
