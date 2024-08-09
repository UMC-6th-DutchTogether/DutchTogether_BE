package com.umc.DutchTogether.domain.receipt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReceiptResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "영수증 인식 응답 DTO")
    public static class ReceiptResponseDTO {
        private Long receiptId;
        private String items;
        private int totalAmount;
    }
}
