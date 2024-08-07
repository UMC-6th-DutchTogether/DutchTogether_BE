package com.umc.DutchTogether.global.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.umc.DutchTogether.global.config.AmazonConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager{

    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig;

    private final UuidRepository uuidRepository;

    public String uploadFile(String keyName, MultipartFile file){ return null;}

    public String generateReceiptKeyName(Uuid uuid) {
        return amazonConfig.getReceiptPath() + '/' + uuid.getUuid();
    }
}