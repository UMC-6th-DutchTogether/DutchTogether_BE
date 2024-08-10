package com.umc.DutchTogether.global.apiPayload.code.status;

import com.umc.DutchTogether.global.apiPayload.code.BaseErrorCode;
import com.umc.DutchTogether.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 미팅 관련 에러
    MEETING_NOT_VALID_ID(HttpStatus.UNAUTHORIZED, "MEETING4001", "ID를 다시 입력해주세요."),
    MEETING_NOT_VALID_PW(HttpStatus.UNAUTHORIZED, "MEETING4001", "비밀번호를 다시 입력해주세요."),
    MEETING_NOT_FOUND(HttpStatus.BAD_REQUEST,"MEETING4002","모임이 존재하지 않습니다."),
    MEETING_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,"MEETING4002","해당 아이디가 이미 존재합니다."),

    // 정산하기 관련 에러
    SETTLEMENT_NOT_FOUND_ID(HttpStatus.NOT_FOUND,"SETTLEMENT4004","settlement ID를 찾을 수 없습니다."),
    SETTLEMENT_NOT_FOUND_BY_MEETING(HttpStatus.NOT_FOUND,"SETTLEMENT4004","해당 meeting 연결 된 settlement 없습니다."),
    SETTLEMENT_NOT_FOUND_BY_SETTLER(HttpStatus.NOT_FOUND,"SETTLEMENT4004","해당 settler 연결 된 settlement 없습니다."),
    SETTLEMENT_LIST_EMPTY(HttpStatus.NOT_FOUND,"SETTLEMENT4000","settlement list 가 비어있습니다."),

    // 정산하기-정산자 관련에러
    SETTLEMENT_SETTLER_NOT_FOUND_BY_SETTLER(HttpStatus.NOT_FOUND,"SETTLEMENT_SETTLER4004","해당 settler 연결 된 settlement-settler 없습니다."),

    // 결제자 관련 에러
    PAYER_NOT_FOUND_BY_SETTLEMENT_ID(HttpStatus.NOT_FOUND,"PAYER4004","payer 찾을 수 없습니다."),
    PAYER_LIST_EMPTY(HttpStatus.NOT_FOUND,"PAYER4000","payer list 비어있습니다."),
    PAYER_LIST_NOT_FOUND(HttpStatus.NOT_FOUND,"PAYER4000","payer 가 없습니다."),

    // 정산자 관련 에러
    SETTLER_NOT_FOUND_BY_NAME(HttpStatus.NOT_FOUND,"SETTLER4004","해당 이름에 맞는 settler 없습니다."),
    SETTLER_NOT_FOUND_BY_ID(HttpStatus.NOT_FOUND,"SETTLER4004","해당 ID에 맞는 settler 없습니다."),

    // 영수증 관련 에러
    RECEIPT_NOT_FOUND(HttpStatus.NOT_FOUND,"RECEIPT4004","영수증을 찾을 수 없습니다."),

    // 정산현황 관련 에러
    SETTLEMENT_STATUS_NOT_FOUND(HttpStatus.NOT_FOUND,"SETTLEMENT_STATUS4004","정산 현황을 찾을 수 없습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}