package com.umc.DutchTogether.global.validation.validator;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.global.validation.annotation.ExistMeeting;
import com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExistMeetingValidator implements ConstraintValidator<ExistMeeting, Long> {

    private final MeetingRepository meetingRepository;

    @Override
    public void initialize(ExistMeeting constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long meetingNum, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Meeting> meeting = meetingRepository.findById(meetingNum);
        if (meeting.isEmpty()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.MEETING_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
