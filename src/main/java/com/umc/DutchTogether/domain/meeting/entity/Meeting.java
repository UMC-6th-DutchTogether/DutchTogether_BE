package com.umc.DutchTogether.domain.meeting.entity;

import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Meeting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "meeting" , cascade = CascadeType.ALL)
    private List<Settlement> settlements = new ArrayList<>();

    @Column(length = 20)
    private String meetingId;

    @Column(length = 20)
    private String password;

    @Column(length = 20)
    private String name;

    private String link;
}
