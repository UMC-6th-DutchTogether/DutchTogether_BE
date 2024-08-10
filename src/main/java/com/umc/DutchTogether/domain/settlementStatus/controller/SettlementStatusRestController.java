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

    @GetMapping("/settlers")
    public ApiResponse<SettlementStatusResponse.SettlementSettlersDTO> findSettlerStatusByName (
            @RequestParam Long settlementId, @RequestParam String settlerName) {
        SettlementStatusResponse.SettlementSettlersDTO settler = settlementStatusQueryService.findSettler(settlementId, settlerName);
        return ApiResponse.onSuccess(settler);
    }

    @PostMapping("settler")
    public ApiResponse<Boolean> addSettler(@Valid @RequestBody SettlementStatusRequest.settlerInfo request){
        Boolean result = settlementStatusCommandService.addSettler(request);
        return ApiResponse.onSuccess(result);
    }
// 입금자 추가 api
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

}