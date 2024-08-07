package com.umc.DutchTogether.domain.receipt.converter;

import com.umc.DutchTogether.domain.receipt.dto.ReceiptResponse;
import com.umc.DutchTogether.domain.receipt.entity.Receipt;

import java.util.List;

public class ReceiptConverter {

    public static Receipt toReceipt(String imageUrl) {
        return Receipt.builder()
                .imageUrl(imageUrl)
                .build();
    }

    public static ReceiptResponse.ReceiptResponseDTO toReceiptResponseDTO(String receiptId, List<String> items, int totalAmount) {
        String itemsString = String.join(", ", items);
        return ReceiptResponse.ReceiptResponseDTO.builder()
                .receiptId(receiptId)
                .items(itemsString)
                .totalAmount(totalAmount)
                .build();
    }
}
