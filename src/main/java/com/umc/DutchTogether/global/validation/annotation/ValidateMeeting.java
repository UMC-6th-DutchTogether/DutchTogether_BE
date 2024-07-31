package com.umc.DutchTogether.global.validation.annotation;

import com.umc.DutchTogether.global.validation.validator.MeetingValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// 아이디와 패스워드를 검증하는 Annotation
@Documented
@Constraint(validatedBy = {MeetingValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateMeeting {
    String message() default "ID,PW가 없습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
