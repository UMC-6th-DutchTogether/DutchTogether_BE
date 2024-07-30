package com.umc.DutchTogether.domain.meeting.validation.annotation;

import com.umc.DutchTogether.domain.meeting.validation.validator.MeetingExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {MeetingExistValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistMeeting {

    String message() default "ID,PW가 없습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
