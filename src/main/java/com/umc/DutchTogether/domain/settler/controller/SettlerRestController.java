package com.umc.DutchTogether.domain.settler.controller;

import com.umc.DutchTogether.domain.settler.dto.SettlerRequest;
import com.umc.DutchTogether.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/settler")
public class SettlerRestController {

    @PostMapping("/")
    public ApiResponse<Boolean> createSettler(@RequestBody SettlerRequest.SettlerRequestDTO request) {

        return ApiResponse.onSuccess(null);
    }
}
