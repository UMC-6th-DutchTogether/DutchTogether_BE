package com.umc.DutchTogether.global.validation.validator;

import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus;
import com.umc.DutchTogether.global.validation.annotation.ExistSettlement;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExistSettlementValidator implements ConstraintValidator<ExistSettlement, Long> {

    private final SettlementRepository settlementRepository;

    @Override
    public void initialize(ExistSettlement constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long settlementId, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Settlement> settlement = settlementRepository.findById(settlementId);
        if (settlement.isEmpty()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.SETTLEMENT_NOT_FOUND_ID.getMessage()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
