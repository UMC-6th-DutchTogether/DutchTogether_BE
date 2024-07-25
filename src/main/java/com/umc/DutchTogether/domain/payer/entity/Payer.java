package com.umc.DutchTogether.domain.payer.entity;

import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Payer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "payer",fetch = FetchType.LAZY)
    private Settlement settlement;

    @Column(length = 20)
    private String name;

    private Long accountNum;

    @Column(length = 10)
    private String bank;
}
