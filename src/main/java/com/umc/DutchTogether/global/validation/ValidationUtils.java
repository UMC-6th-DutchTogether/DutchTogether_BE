package com.umc.DutchTogether.global.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationUtils {

    public static String getFirstErrorMessage(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                return fieldError.getDefaultMessage();
            } else {
                return bindingResult.getGlobalError().getDefaultMessage();
            }
        }
        return null;
    }
}
