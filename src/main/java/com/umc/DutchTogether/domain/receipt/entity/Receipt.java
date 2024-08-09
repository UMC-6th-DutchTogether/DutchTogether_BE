package com.umc.DutchTogether.domain.receipt.entity;

import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Receipt extends BaseEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String imageUrl;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "settlement_id")
        private Settlement settlement;
}
