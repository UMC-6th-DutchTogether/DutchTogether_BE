package com.umc.DutchTogether.domain.settler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class SettlerRequest {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산자 생성 요청 DTO")
    public static  class SettlerRequestDTO{
        private List<SettlerSettlementsDTO> requests;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산자 생성 요청 DTO")
    public static  class SettlerSettlementsDTO{
        private String name;
        private Long settlementId;
    }
}
