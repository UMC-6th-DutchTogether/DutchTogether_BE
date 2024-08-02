package com.umc.DutchTogether.global.apiPayload.exception.handler;

import com.umc.DutchTogether.global.apiPayload.code.BaseErrorCode;
import com.umc.DutchTogether.global.apiPayload.exception.GeneralException;

public class SettlementStatusHandler extends GeneralException {
    public SettlementStatusHandler(BaseErrorCode code){
        super(code);
    }
}
