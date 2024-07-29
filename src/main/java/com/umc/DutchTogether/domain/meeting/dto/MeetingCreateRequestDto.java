package com.umc.DutchTogether.domain.meeting.dto;

import com.umc.DutchTogether.domain.meeting.validation.ConditionalValidPassword;
import com.umc.DutchTogether.domain.meeting.validation.ValidPassword;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@Builder
@ConditionalValidPassword
public class MeetingCreateRequestDto {

    @Size(min = 2, max = 12)
    private String meetingId;

    @ValidPassword
    private String password;

    @NotBlank(message = "모임의 이름은 필수입니다.")
    private String meetingName;
}
