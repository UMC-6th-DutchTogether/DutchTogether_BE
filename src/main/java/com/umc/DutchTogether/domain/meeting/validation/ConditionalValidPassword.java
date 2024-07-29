package com.umc.DutchTogether.domain.meeting.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConditionalValidPasswordValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionalValidPassword {
    String message() default "Invalid password condition";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}