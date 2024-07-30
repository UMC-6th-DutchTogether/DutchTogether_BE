package com.umc.DutchTogether.global.apiPayload.exception.handler;

import com.umc.DutchTogether.global.apiPayload.code.BaseErrorCode;
import com.umc.DutchTogether.global.apiPayload.exception.GeneralException;

public class SettlementHandler extends GeneralException {
    public SettlementHandler(BaseErrorCode code) {
        super(code);
    }
}
