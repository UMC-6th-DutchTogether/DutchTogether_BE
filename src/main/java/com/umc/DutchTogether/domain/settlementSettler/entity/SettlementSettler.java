package com.umc.DutchTogether.domain.settlementSettler.entity;

import com.umc.DutchTogether.domain.settler.entity.Settler;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.global.common.BaseEntity;
import com.umc.DutchTogether.global.validation.annotation.UniqueSettler;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    // 엔티티가 persist 되기 전에 status 필드의 기본값을 PENDING 으로 설정
    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = Status.PENDING;
        }
    }
}
