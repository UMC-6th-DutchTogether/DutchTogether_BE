package com.umc.DutchTogether.global.apiPayload.exception.handler;

import com.umc.DutchTogether.global.apiPayload.code.BaseErrorCode;
import com.umc.DutchTogether.global.apiPayload.exception.GeneralException;

public class MeetingHandler extends GeneralException {

    public MeetingHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}
