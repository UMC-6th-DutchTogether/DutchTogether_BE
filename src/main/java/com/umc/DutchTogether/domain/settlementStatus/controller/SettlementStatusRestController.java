package com.umc.DutchTogether.domain.settlementStatus.controller;

import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusRequest;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;
import com.umc.DutchTogether.domain.settlementStatus.service.SettlementStatusCommandService;
import com.umc.DutchTogether.domain.settlementStatus.service.SettlementStatusQueryService;
import com.umc.DutchTogether.global.apiPayload.ApiResponse;
import com.umc.DutchTogether.global.validation.annotation.ExistMeeting;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/settlementStatus")
public class SettlementStatusRestController {

    private final SettlementStatusQueryService settlementStatusQueryService;
    private final SettlementStatusCommandService settlementStatusCommandService;

    @PostMapping("/login")
    public ApiResponse<SettlementStatusResponse.SettlementStatusLoginDTO> login(@Valid @RequestBody SettlementStatusRequest.SettlementStatusDTO request) {
        SettlementStatusResponse.SettlementStatusLoginDTO status = settlementStatusCommandService.login(request);
        return ApiResponse.onSuccess(status);
    }

    @GetMapping("/{meetingNum}")
    public ApiResponse<SettlementStatusResponse.SettlementStatusDTO> getSettlementStatus(@ExistMeeting @PathVariable Long meetingNum) {
        SettlementStatusResponse.SettlementStatusDTO status = settlementStatusQueryService.getStatus(meetingNum);
        return ApiResponse.onSuccess(status);
    }

// 입금자에 대한 입금 시각을 추가하는 api로 수정 예정    
//    @PostMapping("/{statusId}/settlers")
//    public ResponseEntity<ApiResponse<Void>> addSettler(
//            @PathVariable Long statusId,
//            @RequestBody SettlementStatusRequest.AddSettlerRequest request) {
//        try {
//            settlementStatusCommandService.addSettler(statusId, request.getSettlerName());
//            return ResponseEntity.ok(ApiResponse.onSuccess(null));
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(ApiResponse.onFailure("404", e.getMessage(), null));
//        }
//    }

//    @GetMapping("/{statusId}/settlers")
//    public ApiResponse<SettlementStatusResponse.SettlementSettlerResponseDTO> getSettlerStatus(
//            @PathVariable Long statusId, @RequestParam String settlerName) {
//        try {
//            SettlementStatusResponse.SettlementSettlerResponse settlerDTO = settlementStatusQueryService.getSettler(statusId, settlerName);
//            return ResponseEntity.ok(ApiResponse.onSuccess(settlerDTO));
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(ApiResponse.onFailure("404", e.getMessage(), null));
//        }
//    }


}