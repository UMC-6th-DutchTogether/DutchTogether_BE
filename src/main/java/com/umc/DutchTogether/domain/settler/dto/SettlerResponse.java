package com.umc.DutchTogether.domain.settler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class SettlerResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산자 리스트 응답 DTO")
    public static class settlerResponseDTO {
        private String meetingName;
        private List<settlerDTO> settlers;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산자 응답 DTO")
    public static class settlerDTO {
        private String name;
        private Long settlerId;
    }
}
