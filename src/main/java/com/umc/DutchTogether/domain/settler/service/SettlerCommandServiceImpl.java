package com.umc.DutchTogether.domain.settler.service;

import com.umc.DutchTogether.domain.settler.dto.SettlerRequest;
import com.umc.DutchTogether.domain.settler.entity.Settler;
import com.umc.DutchTogether.domain.settler.repository.SettlerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlerCommandServiceImpl implements SettlerCommandService {

    private final SettlerRepository settlerRepository;

    @Override
    public Boolean createSettler(SettlerRequest.SettlerRequestDTO request) {
        List<String> settlerNames = request.getNames();
        settlerNames.stream()
                .map(String->{
                    Settler settle = Settler.builder()
                            .name(String)
                            .build();
                    Settler savedSettler = settlerRepository.save(settle);
                    // 이제 정산하기- 정산자 저장해야함
                })
    }
}
