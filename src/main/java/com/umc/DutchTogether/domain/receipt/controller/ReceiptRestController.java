package com.umc.DutchTogether.domain.receipt.controller;

import com.umc.DutchTogether.domain.receipt.dto.ReceiptResponse;
import com.umc.DutchTogether.domain.receipt.service.ReceiptCommandService;
import com.umc.DutchTogether.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/receipt")
public class ReceiptRestController {

    private final ReceiptCommandService receiptCommandService;

    @PostMapping(value = "/recognize",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse<ReceiptResponse.ReceiptResponseDTO> recognizeReceipt(@RequestPart("file") MultipartFile file) {
        ReceiptResponse.ReceiptResponseDTO receipt = receiptCommandService.recognizeReceipt(file);
        return ApiResponse.onSuccess(receipt);
    }
}
