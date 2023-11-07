package com.hsg.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "merchantPos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
@ToString
public class MerchantPos {

    @Id
    @SequenceGenerator(name = "seq_merchantPos", allocationSize = 1)
    @Column(name = "merchantPosId")
    private String merchantPosId;

    @Column(name = "merchantName")
    private String merchantName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchantPos")
    private List<Payment> payments;

    @OneToMany(mappedBy = "merchantPos")
    private List<Payback> paybacks;
}
