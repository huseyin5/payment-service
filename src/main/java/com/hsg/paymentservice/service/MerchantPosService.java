package com.hsg.paymentservice.service;

import com.hsg.paymentservice.dtos.MerchantPosRequestDto;
import com.hsg.paymentservice.dtos.MerchantPosResponseDto;
import com.hsg.paymentservice.entity.MerchantPos;
import com.hsg.paymentservice.repository.MerchantPosRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
}