package com.umc.DutchTogether.domain.settlement.dto;

import com.umc.DutchTogether.global.validation.annotation.ExistMeeting;
import com.umc.DutchTogether.global.validation.annotation.ExistReceipt;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
        @Digits(integer = 100, fraction = 0, message = "계좌번호는 문자가 포함될 수 없습니다.")
        private Long accountNumber;

        @NotNull(message = "예금주 이름을 입력해주세요.")
        @NotBlank(message = "예금주 이름을 입력해주세요.")
        @Pattern(regexp = "^[가-힣]+$", message = "예금주 이름은 한국어로 입력되어야 합니다.")
        private String payer;

        @NotNull(message = "정산하는 금액을 입력해 주세요.")
        private int totalAmount;

        @NotNull(message = "정산하는 인원의 수를 입력해주세요.")
        private int numPeople;

        @ExistReceipt
        private Long receiptId;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산하기 생성 요청 DTO")
    public static class SettlementInfoListDTO{
        private List<SettlementInfoDTO> settlementInfoList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산하기 생성 요청 DTO")
    public static class SettlementInfoDTO{
        private String item;
        private Long settlementId;
        private int totalAmount;
        @ExistReceipt
        private Long receiptId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "여러명 정산하기 - 정산하기 테이블 생성 요청 DTO")
    public static class SettlementPayerDTO{
        @NotNull
        private Long payerId;
        @NotNull
        private Long meetingNum;
    }
}
