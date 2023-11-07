package com.hsg.paymentservice.dtos;

import com.hsg.paymentservice.entity.Payback;
import com.hsg.paymentservice.entity.Payment;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantPosResponseDto {

    private String merchantPosId;
    private String merchantName;
    private List<Payment> payments;
    private List<Payback> paybacks;
}
