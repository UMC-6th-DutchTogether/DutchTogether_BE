package com.umc.DutchTogether.domain.settlement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SingleSettlementInfoResponseDto {
    private String meetingName;
    private String payer;
    private Long account_num;
    private String bank;
    private int total_amount;
    private int participants;
}
