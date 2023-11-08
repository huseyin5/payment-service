package com.hsg.paymentservice.service;

import com.hsg.paymentservice.dtos.MerchantPosRequestDto;
import com.hsg.paymentservice.dtos.MerchantPosResponseDto;
import com.hsg.paymentservice.entity.MerchantPos;
import com.hsg.paymentservice.entity.Payback;
import com.hsg.paymentservice.repository.MerchantPosRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class MerchantPosService {

    private final MerchantPosRepository merchantPosRepository;
    private final ModelMapper modelmapper;

    public List<MerchantPos> getAllMerchantPos() {
        return merchantPosRepository.findAll();
    }

    @Transactional
    public MerchantPosRequestDto saveMerchantPos(MerchantPosRequestDto merchantPosRequestDto) {
        MerchantPos merchantPos = modelmapper.map(merchantPosRequestDto, MerchantPos.class);
        merchantPosRepository.save(merchantPos);
        return merchantPosRequestDto;
    }

    public MerchantPosResponseDto getMerchantDetail(String id) {
        MerchantPos merchantPos = merchantPosRepository.findById(id).orElseThrow(() -> new RuntimeException("Merchant not found"));
        return modelmapper.map(merchantPos, MerchantPosResponseDto.class);
    }

    public String getMerchantPosIdTotalPaybackAmount(String merchantPosId) {

        List<MerchantPos> merchantPosList = merchantPosRepository.findAllByMerchantPosId(merchantPosId);

        float totalPaybackAmount = 0;

        for (MerchantPos merchantPos : merchantPosList) {
            for (Payback payback : merchantPos.getPaybacks()) {
                totalPaybackAmount += payback.getPaybackAmount();
            }
        }

        return "Merchant pos id: " + merchantPosId + " total payback amount: " + totalPaybackAmount + " TL";
    }
}
