package com.umc.DutchTogether.global.apiPayload.exception.handler;

import com.umc.DutchTogether.global.apiPayload.code.BaseErrorCode;
import com.umc.DutchTogether.global.apiPayload.exception.GeneralException;

public class SettlerHandler extends GeneralException {
    public SettlerHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
