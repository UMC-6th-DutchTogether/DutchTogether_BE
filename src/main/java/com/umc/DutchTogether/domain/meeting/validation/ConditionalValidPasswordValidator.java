package com.umc.DutchTogether.domain.meeting.validation;

import com.umc.DutchTogether.domain.meeting.dto.MeetingCreateRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConditionalValidPasswordValidator implements ConstraintValidator<ConditionalValidPassword, MeetingCreateRequestDto> {

    @Override
    public void initialize(ConditionalValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(MeetingCreateRequestDto dto, ConstraintValidatorContext context) {
        String meetingId = dto.getMeetingId();
        String password = dto.getPassword();

        // ID가 존재할 시, 비밀번호는 필수
        if (meetingId != null && (password == null || password.isEmpty())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("비밀번호를 입력해 주세요.")
                    .addPropertyNode("password")
                    .addConstraintViolation();
            return false;
        }

        // ID 존재X -> 비밀번호 입력 거부
        if (meetingId == null && (password != null && !password.isEmpty())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("비밀번호는 저장되지 않습니다.")
                    .addPropertyNode("password")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}