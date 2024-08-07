package com.umc.DutchTogether.domain.settlement.dto;

import com.umc.DutchTogether.global.validation.annotation.ExistMeeting;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SettlementRequest {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산하기 생성 요청 DTO")
    public static class SettlementDTO{

        @NotNull(message = "모임 번호를 요청에 포함해주세요.")
        @Min(value = 0, message = "모임 번호는 음수일 수 없습니다.")
        @ExistMeeting
        private Long meetingNum;

        @NotNull(message = "은행 이름을 입력해주세요.")
        @Pattern(regexp = "^[가-힣]+$", message = "은행 이름은 한국어로 입력되어야 합니다.")
        private String bankName;

        @NotNull(message = "계좌번호를 입력해주세요.")
        @Digits(integer = 20, fraction = 0, message = "계좌번호는 문자가 포함될 수 없습니다.")
        private Long accountNumber;

        @NotNull(message = "예금주 이름을 입력해주세요.")
        @NotBlank(message = "예금주 이름을 입력해주세요.")
        @Pattern(regexp = "^[가-힣]+$", message = "예금주 이름은 한국어로 입력되어야 합니다.")
        private String payer;

        @NotNull(message = "정산하는 금액을 입력해 주세요.")
        @Min(value = 1000, message = "정산 금액은 1,000 원 이상이어야 합니다.")
        @Max(value = 1000000, message = "정산 금액은 최대 1,000,000 원 입니다.")
        private int totalAmount;

        @NotNull(message = "정산하는 인원의 수를 입력해주세요.")
        @Min(value = 2, message = "정산 인원은 최소 2명 입니다.")
        @Max(value = 10, message = "정산 인원은 최대 10명 입니다.")
        private int numPeople;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산하기 생성 요청 DTO")
    public static class SettlementInfoListDTO{

    }
}
