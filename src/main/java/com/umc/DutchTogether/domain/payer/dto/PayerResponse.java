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

    // 위 아래 뭐가 좋을지 모르겠음
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "결제자 이름 응답 DTO")
    public static class PayerNameListDTO{
        private List<String> names;
    }
}
