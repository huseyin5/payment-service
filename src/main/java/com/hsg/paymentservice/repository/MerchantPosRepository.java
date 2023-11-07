package com.hsg.paymentservice.repository;

import com.hsg.paymentservice.entity.MerchantPos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantPosRepository extends JpaRepository<MerchantPos, String> {
    MerchantPos findByMerchantPosId(String merchantPosId);


}
