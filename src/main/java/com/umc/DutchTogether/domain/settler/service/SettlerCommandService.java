package com.umc.DutchTogether.domain.settler.service;

import com.umc.DutchTogether.domain.settler.dto.SettlerRequest;
import com.umc.DutchTogether.domain.settler.entity.Settler;

public interface SettlerCommandService {
    // get 제외

    public Boolean createSettler(SettlerRequest.SettlerRequestDTO request);
}
