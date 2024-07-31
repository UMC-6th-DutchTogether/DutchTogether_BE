package com.umc.DutchTogether.global.validation.validator;

import com.umc.DutchTogether.domain.meeting.dto.MeetingRequest;
import com.umc.DutchTogether.global.validation.annotation.ValidateMeeting;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingValidator implements ConstraintValidator<ValidateMeeting, MeetingRequest.MeetingDT0> {

    @Override
    public void initialize(ValidateMeeting constraintAnnotation) {
    }

    @Override
    public boolean isValid(MeetingRequest.MeetingDT0 meetingDT0, ConstraintValidatorContext constraintValidatorContext) {

        String meetingId = meetingDT0.getMeetingId();
        String password = meetingDT0.getPassword();
        if( (meetingId == null || meetingId.isEmpty() ) && (password == null || password.isEmpty())) {
            return true;
        }

        if (meetingId == null) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("아이디를 다시 입력해주세요.")
                    .addPropertyNode("meetingId")
                    .addConstraintViolation();
            return false;
        }
        if (password == null) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("비밀번호를 다시 입력해주세요.")
                    .addPropertyNode("password")
                    .addConstraintViolation();
            return false;
        }

        // ID 존재하는 경우, 비밀번호 필수
        if ((!meetingId.isBlank()) && (password.isBlank())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("비밀번호를 입력해 주세요.")
                    .addPropertyNode("password")
                    .addConstraintViolation();
            return false;
        }

        // ID 존재하지 않는 경우, 비밀번호 저장하지 않습니다.
        if ((meetingId.isBlank()) && (!password.isBlank())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("아이디를 입력해주세요.")
                    .addPropertyNode("meetingId")
                    .addConstraintViolation();
            return false;
        }

        return true;
        }
}
