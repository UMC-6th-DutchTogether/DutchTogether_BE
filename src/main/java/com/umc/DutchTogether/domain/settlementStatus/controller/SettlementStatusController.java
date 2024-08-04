package com.umc.DutchTogether.domain.settlementStatus.controller;

import com.umc.DutchTogether.domain.settlementStatus.converter.SettlementStatusConverter;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusRequest;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;
import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;
import com.umc.DutchTogether.domain.settlementStatus.service.SettlementStatusCommandService;
import com.umc.DutchTogether.domain.settlementStatus.service.SettlementStatusCommandServiceImpl;
import com.umc.DutchTogether.domain.settlementStatus.service.SettlementStatusQueryService;
import com.umc.DutchTogether.domain.settlementStatus.service.SettlementStatusQueryServiceImpl;
import com.umc.DutchTogether.global.apiPayload.ApiResponse;
import io.swagger.annotations.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/settlementStatus")
public class SettlementStatusController {

    private final SettlementStatusQueryService settlementStatusQueryService;
    private final SettlementStatusCommandService settlementStatusCommandService;

    @GetMapping("/{statusId}")
    public ResponseEntity<ApiResponse<SettlementStatusResponse.SettlementStatusDTO>> getSettlementStatus(@PathVariable Long statusId) {
        try {
            SettlementStatusResponse.SettlementStatusDTO statusDTO = settlementStatusQueryService.getStatus(statusId);
            return ResponseEntity.ok(ApiResponse.onSuccess(statusDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.onFailure("404", e.getMessage(), null));
        }
    }

    @PostMapping("/{statusId}")
    public ResponseEntity<ApiResponse<Void>> addSettler(
            @PathVariable Long statusId,
            @RequestBody SettlementStatusRequest.AddSettlerRequest request) {
        try {
            settlementStatusCommandService.addSettler(statusId, request.getSettlerName());
            return ResponseEntity.ok(ApiResponse.onSuccess(null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.onFailure("404", e.getMessage(), null));
        }
    }

    @GetMapping("/{statusId}/settlers")
    public ResponseEntity<ApiResponse<SettlementStatusResponse.SettlementSettlerResponse>> getSettlerStatus(
            @PathVariable Long statusId, @RequestParam String settlerName) {
        try {
            SettlementStatusResponse.SettlementSettlerResponse settlerDTO = settlementStatusQueryService.getSettler(statusId, settlerName);
            return ResponseEntity.ok(ApiResponse.onSuccess(settlerDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.onFailure("404", e.getMessage(), null));
        }
    }


}