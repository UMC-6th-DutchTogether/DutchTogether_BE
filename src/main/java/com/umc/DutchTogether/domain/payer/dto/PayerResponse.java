package com.umc.DutchTogether.domain.payer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class PayerResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "결제자 생성 응답 DTO")
    public static class PayerListDTO{
        private List<PayerDTO> payers;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "결제자 id DTO")
    public static class PayerDTO{
        private Long payerId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "결제자 이름 응답 DTO")
    public static class PayerNameListDTO{
        private List<PayerNameDTO> names;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "결제자 이름,정산하기Id DTO")
    public static class PayerNameDTO{
        private String name;
        private Long settlementId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "결제자 정보 응답 DTO")
    public static class PayerInfoListDTO{
        private List<PayerInfoDTO> PayerInfos;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "결제자 정보 DTO")
    public static class PayerInfoDTO{
        private String name;
        private String bank;
        private Long accountNum;
        private Float shareAmount;
    }
}
