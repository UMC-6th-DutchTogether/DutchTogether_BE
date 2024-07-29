package com.umc.DutchTogether.domain.meeting.service;

import com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus;
import com.umc.DutchTogether.global.apiPayload.exception.handler.MeetingHandler;
import org.springframework.stereotype.Service;


@Service
public class MeetingCommandServiceImpl implements MeetingCommandService{
    @Override
    public void CheckValidPassword(String password) {
        if(password == null || password.isEmpty()){
            throw new MeetingHandler(ErrorStatus._UNAUTHORIZED);
        }
    }
}
