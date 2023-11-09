package com.hsg.paymentservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payback")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
@ToString
public class Payback {

    @Id
    @SequenceGenerator(name = "seq_payback", allocationSize = 1)
    @Column(name = "paybackId")
    private String paybackId = UUID.randomUUID().toString();

    @Column(name = "paybackAmount")
    private float paybackAmount;

    @Column(name = "profitAmount")
    private float profitAmount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "merchantPosId")
    private MerchantPos merchantPos;


    @Column(name = "paybackDate")
    private LocalDateTime paybackDate;

    public LocalDateTime getPaybackDate() {
        return paybackDate;
    }

    public void setPaybackDate(LocalDateTime paybackDate) {
        this.paybackDate = paybackDate = LocalDateTime.now();
    }
}
