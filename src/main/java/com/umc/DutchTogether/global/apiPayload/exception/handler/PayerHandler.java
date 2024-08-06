package com.umc.DutchTogether.global.apiPayload.exception.handler;

import com.umc.DutchTogether.global.apiPayload.code.BaseErrorCode;
import com.umc.DutchTogether.global.apiPayload.exception.GeneralException;

public class PayerHandler extends GeneralException {
    public PayerHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}
