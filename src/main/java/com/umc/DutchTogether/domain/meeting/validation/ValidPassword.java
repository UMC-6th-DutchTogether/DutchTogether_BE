//package com.umc.DutchTogether.domain.meeting.validation;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.*;
//
//@Documented
//@Constraint(validatedBy = PasswordValidator.class)
//@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
//@Retention(RetentionPolicy.RUNTIME)
//public @interface ValidPassword {
//    String message() default "Invalid password";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}