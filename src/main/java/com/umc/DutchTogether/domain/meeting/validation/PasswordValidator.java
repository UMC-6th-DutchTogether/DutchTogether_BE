//package com.umc.DutchTogether.domain.meeting.validation;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//
//public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
//
//    @Override
//    public void initialize(ValidPassword constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//
//
//        if (value == null || value.isEmpty()) {
//            return true; // nullable 허용
//        }
//
//        /**
//         * 비밀번호 검증
//         * 길이: 최소 8 ~ 최대 16
//         * 형식: 문자 + 숫자
//         */
//        boolean isValid = true;
//
//        // 길이 검증
//        if (value.length() < 8 || value.length() > 16) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate("비밀번호의 길이는 최소 8자리, 최대 16자리 입니다.")
//                    .addConstraintViolation();
//            isValid = false;
//        }
//
//        // 형식 검증
//        if (!value.matches("[a-zA-Z0-9]*")) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate("비밀번호는 문자와 숫자를 혼합해야합니다.")
//                    .addConstraintViolation();
//            isValid = false;
//        }
//
//        return isValid;
//    }
//}