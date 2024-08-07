package com.umc.DutchTogether.domain.receipt.service;

import com.umc.DutchTogether.domain.receipt.dto.ReceiptResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ReceiptCommandService {
    public ReceiptResponse.ReceiptResponseDTO recognizeReceipt(MultipartFile file);
}
