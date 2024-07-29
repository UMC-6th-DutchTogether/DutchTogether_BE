package com.umc.DutchTogether.domain.settlement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SingleSettlementCreateRequestDto {
    private Long meeting_num;
    private String bankName;
    private Long accountNumber;
    private String accountHolder;
    private int totalAmount;
    private int participants;
}
