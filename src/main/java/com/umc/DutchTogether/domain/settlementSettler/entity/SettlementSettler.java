package com.umc.DutchTogether.domain.settlementSettler.entity;

import com.umc.DutchTogether.domain.settler.entity.Settler;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter//추가했음
@Builder
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SettlementSettler extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "settlement_id")
    private Settlement settlement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "settler_id")
    private Settler settler;

    @Enumerated(EnumType.STRING)
    private Status status;
}
