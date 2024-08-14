package com.umc.DutchTogether.global.validation.validator;

import com.umc.DutchTogether.domain.meeting.dto.MeetingRequest;
import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus;
import com.umc.DutchTogether.global.validation.annotation.UniqueMeeting;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueMeetingValidator implements ConstraintValidator<UniqueMeeting, MeetingRequest.MeetingDT0> {

    private final MeetingRepository meetingRepository;

    @Override
    public void initialize(UniqueMeeting constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MeetingRequest.MeetingDT0 meetingDTO, ConstraintValidatorContext constraintValidatorContext) {
        boolean meetingIdExist = meetingRepository.existsByMeetingId(meetingDTO.getMeetingId());
        if (meetingIdExist) {
            boolean meetingPasswordExist = meetingRepository.existsByPassword(meetingDTO.getPassword());
            if (meetingPasswordExist) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.MEETING_ALREADY_EXISTS.getMessage())
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
