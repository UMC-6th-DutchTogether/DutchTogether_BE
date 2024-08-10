package com.umc.DutchTogether.global.validation.validator;

import com.umc.DutchTogether.domain.receipt.repository.ReceiptRepository;
import com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus;
import com.umc.DutchTogether.global.validation.annotation.ExistReceipt;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistReceiptValidator implements ConstraintValidator<ExistReceipt, Long> {

    private final ReceiptRepository receiptRepository;

    @Override
    public void initialize(ExistReceipt constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long receiptId, ConstraintValidatorContext constraintValidatorContext) {
        if (receiptId == null || receiptId == 0) {
            return true;
        }
        boolean exists = receiptRepository.existsById(receiptId);
        if (!exists) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.RECEIPT_NOT_FOUND.getMessage()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
