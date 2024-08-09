package com.umc.DutchTogether.domain.meeting.repository;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    Optional<Meeting> findByLink(String link);
    Optional<Meeting> findByMeetingIdAndPassword(String meetingId, String password);
}

