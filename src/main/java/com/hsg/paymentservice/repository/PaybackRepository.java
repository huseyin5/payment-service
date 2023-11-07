package com.hsg.paymentservice.repository;

import com.hsg.paymentservice.entity.Payback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaybackRepository extends JpaRepository<Payback, String> {
}
