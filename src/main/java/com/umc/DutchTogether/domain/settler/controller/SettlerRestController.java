package com.umc.DutchTogether.domain.settler.controller;

import com.umc.DutchTogether.domain.settler.dto.SettlerRequest;
import com.umc.DutchTogether.domain.settler.dto.SettlerResponse;
import com.umc.DutchTogether.domain.settler.service.SettlerCommandService;
import com.umc.DutchTogether.domain.settler.service.SettlerQueryService;
import com.umc.DutchTogether.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/settler")
public class SettlerRestController {

    private final SettlerCommandService settlerCommandService;
    private final SettlerQueryService settlerQueryService;

    @PostMapping("/")
    public ApiResponse<Boolean> createSettler(@Valid @RequestBody SettlerRequest.SettlerRequestDTO request) {
        Boolean result = settlerCommandService.createSettler(request);
        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/{meetingNum}")
    public ApiResponse<SettlerResponse.settlerResponseDTO> getSettlers(@PathVariable String Link) {
        SettlerResponse.settlerResponseDTO result = settlerQueryService.createSettlers(Link);
        return ApiResponse.onSuccess(result);
    }
}
