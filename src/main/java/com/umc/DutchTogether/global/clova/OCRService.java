package com.umc.DutchTogether.global.clova;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

@Service
public class OCRService {

    @Value("${spring.ocr.service.url}")
    private String apiURL;

    @Value("${spring.ocr.service.secretKey}")
    private String secretKey;

    public String callOCRService(MultipartFile file) throws Exception {
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setUseCaches(false);
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        con.setRequestProperty("X-OCR-SECRET", secretKey);

        JSONObject json = new JSONObject();
        json.put("version", "V2");
        json.put("requestId", UUID.randomUUID().toString());
        json.put("timestamp", System.currentTimeMillis());

        JSONObject image = new JSONObject();
        image.put("format", "jpg");
        image.put("data", file.getBytes());
        image.put("name", "demo");

        JSONArray images = new JSONArray();
        images.put(image);
        json.put("images", images);

        String postParams = json.toString();

        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(postParams);
            wr.flush();
        }

        int responseCode = con.getResponseCode();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(responseCode == 200 ? con.getInputStream() : con.getErrorStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }
}
