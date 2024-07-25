package com.umc.DutchTogether.domain.settlement.entity;

import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;
import com.umc.DutchTogether.global.common.BaseEntity;
import com.umc.DutchTogether.domain.meeting.entity.Meeting;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Settlement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "meeting_num")
    private Meeting meeting;

    @OneToMany(mappedBy = "settlement", cascade = CascadeType.ALL)
    private List<SettlementSettler> settlementSettlers = new ArrayList<>();

    @OneToOne(mappedBy = "settlement",  cascade = CascadeType.ALL)
    private SettlementStatus settlementStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_id")
    private Payer payer;

    private String items;

    private int numPeople;

    //타입 같이 정한 후 영수증 추가해야함 !

    private String link;
}
