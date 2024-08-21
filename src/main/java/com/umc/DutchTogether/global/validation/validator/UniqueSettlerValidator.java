package com.umc.DutchTogether.global.validation.validator;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import com.umc.DutchTogether.domain.settlementSettler.dto.SettlementSettlerRequest;
import com.umc.DutchTogether.domain.settlementSettler.repository.SettlementSettlerRepository;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlementHandler;
import com.umc.DutchTogether.global.validation.annotation.UniqueSettler;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus.SETTLEMENT_NOT_FOUND_ID;

@Component
@RequiredArgsConstructor
public class UniqueSettlerValidator implements ConstraintValidator<UniqueSettler, SettlementSettlerRequest.SettlementSettlerDTO> {

    private final SettlementSettlerRepository settlementSettlerRepository;
    private final SettlementRepository settlementRepository;

    @Override
    public void initialize(UniqueSettler constraintAnnotation) {
    }

    @Override
    public boolean isValid(SettlementSettlerRequest.SettlementSettlerDTO request, ConstraintValidatorContext context) {
        if (request == null) {
            return true;
        }

        Long settlementId = request.getSettlementId();
        String settlerName = request.getSettlerName();
        Settlement settlement = settlementRepository.findById(settlementId)
                .orElseThrow(()->new SettlementHandler(SETTLEMENT_NOT_FOUND_ID));
        Meeting meeting = settlement.getMeeting();
        Long meetingId = meeting.getId();
        List<Settlement> settlements = settlementRepository.findAllByMeetingId(meetingId);

        if (settlements.size() == 1) {
            boolean settlerExists = settlementSettlerRepository.existsBySettlementIdAndSettlerName(settlementId, settlerName);
            if (settlerExists) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("정산자가 중복될 수 없습니다.")
                        .addPropertyNode("settler")
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}