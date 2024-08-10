package com.umc.DutchTogether.domain.payer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class PayerRequest {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "결제자 수정 요청 DTO")
    public static class PayerListDTO {
        @NotNull
        private List<PayerDTO> payers;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "결제자 정보 DTO")
    public static class PayerDTO {
        @Column(length = 10)
        private String bank;
        @NotNull
        private Long account;
        @NotNull
        private Long payerId;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "결제자 생성 요청 DTO")
    public static class PayerNameListDTO {
        @NotNull
        private List<PayerNameDTO> payerNames;
        @NotNull
        private Long meetingNum;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "결제자 이름 DTO")
    public static class PayerNameDTO {
        @Column(length = 20)
        private String name;
    }
}
