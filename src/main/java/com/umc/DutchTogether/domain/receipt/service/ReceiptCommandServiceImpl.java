package com.umc.DutchTogether.domain.receipt.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.DutchTogether.domain.receipt.converter.ReceiptConverter;
import com.umc.DutchTogether.domain.receipt.dto.ReceiptResponse;
import com.umc.DutchTogether.domain.receipt.repository.ReceiptRepository;
import com.umc.DutchTogether.global.aws.s3.AmazonS3Manager;
import com.umc.DutchTogether.global.aws.s3.UuidRepository;
import com.umc.DutchTogether.global.clova.OCRService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReceiptCommandServiceImpl implements ReceiptCommandService {

    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;
    private final ReceiptRepository receiptRepository;
    private final OCRService ocrService;
    private final ObjectMapper objectMapper;

    @Override
    public ReceiptResponse.ReceiptResponseDTO recognizeReceipt(MultipartFile file) {

        File convFile = new File(System.getProperty("java.io.tmpdir"), file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("File conversion error", e);
        }

        String extractedTexts;
        try {
            extractedTexts = ocrService.callOCRService(file);
        } catch (Exception e) {
            throw new RuntimeException("OCR processing error", e);
        }

        // Extract item names and total price from the OCR result
        List<String> items = new ArrayList<>();
        String totalPrice = "";

        try {
            JsonNode rootNode = objectMapper.readTree(extractedTexts);
            JsonNode imagesNode = rootNode.path("images");

            if (imagesNode.isArray()) {
                for (JsonNode imageNode : imagesNode) {
                    JsonNode receiptNode = imageNode.path("receipt").path("result");

                    // Extract item names
                    JsonNode itemsNode = receiptNode.path("subResults").path(0).path("items");
                    for (JsonNode itemNode : itemsNode) {
                        String itemName = itemNode.path("name").path("text").asText();
                        items.add(itemName);
                    }

                    // Extract total price
                    totalPrice = receiptNode.path("totalPrice").path("price").path("text").asText();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("JSON processing error", e);
        }

        int totalAmount = Integer.parseInt(totalPrice.replaceAll("[^0-9]", ""));

        String receiptId = "id";

        return ReceiptConverter.toReceiptResponseDTO(receiptId, items, totalAmount);
    }
}
