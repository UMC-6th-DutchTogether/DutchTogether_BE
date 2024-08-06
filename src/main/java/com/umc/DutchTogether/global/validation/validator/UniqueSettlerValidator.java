package com.umc.DutchTogether.global.validation.validator;

import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementSettler.repository.SettlementSettlerRepository;
import com.umc.DutchTogether.global.validation.annotation.UniqueSettler;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueSettlerValidator implements ConstraintValidator<UniqueSettler, SettlementSettler> {

    private final SettlementSettlerRepository settlementSettlerRepository;

    @Override
    public void initialize(UniqueSettler constraintAnnotation) {
    }

    @Override
    public boolean isValid(SettlementSettler settlementSettler, ConstraintValidatorContext context) {
        if (settlementSettler == null) {
            return true;
        }

        Long settlementId = settlementSettler.getSettlement().getId();
        String settlerName = settlementSettler.getSettler().getName();

        boolean settlerExists = settlementSettlerRepository.existsBySettlementIdAndSettlerName(settlementId, settlerName);

        if (settlerExists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("정산자가 중복될 수 없습니다.")
                    .addPropertyNode("settler")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
