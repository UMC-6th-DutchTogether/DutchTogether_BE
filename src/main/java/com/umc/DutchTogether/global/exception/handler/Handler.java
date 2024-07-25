package com.umc.DutchTogether.global.exception.handler;

import com.umc.DutchTogether.global.apiPayload.code.BaseErrorCode;
import com.umc.DutchTogether.global.exception.GeneralException;

public class Handler extends GeneralException {
    public Handler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
