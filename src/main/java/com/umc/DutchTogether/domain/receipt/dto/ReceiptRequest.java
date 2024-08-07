package com.umc.DutchTogether.domain.receipt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

public class ReceiptRequest {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @NotNull
    @Schema(title = "영수증 인식 요청 DTO")
    public static class ReceiptRequestDTO {
        private MultipartFile receiptImage;
    }
}