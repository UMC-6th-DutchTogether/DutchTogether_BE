package com.umc.DutchTogether.domain.meeting.converter;

import com.umc.DutchTogether.domain.meeting.dto.MeetingRequest;
import com.umc.DutchTogether.domain.meeting.dto.MeetingResponse;
import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.receipt.entity.Receipt;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;

public class MeetingConverter {

    public static Meeting toMeetingDTO(MeetingRequest.MeetingDT0 requestBody) {
        return Meeting.builder()
                .meetingId(requestBody.getMeetingId())
                .password(requestBody.getPassword())
                .build();
    }

    public static MeetingResponse.MeetingResultDT0 toMeetingResultDTO(Meeting meeting) {
        if (meeting == null) {
            return null;
        }
        return MeetingResponse.MeetingResultDT0.builder()
                .meetingNum(meeting.getId())
                .build();
    }

    public static MeetingResponse.MeetingLinkResultDT0 toMeetingLinkResultDTO(Meeting meeting) {
        String domain = "http://www.dutchtogether.com/";

        if (meeting == null) {
            return null;
        }
        return MeetingResponse.MeetingLinkResultDT0.builder()
                .meetingLink(domain + meeting.getLink())
                .build();
    }


    public static MeetingResponse.SingleSettlementResultDTO toSingleSettlementResultDTO(Meeting meeting, Settlement settlement, Payer payer, Receipt receipt) {
        return MeetingResponse.SingleSettlementResultDTO.builder()
                .meetingName(meeting.getName())
                .total_amount(settlement.getTotalAmount())
                .num_people(settlement.getNumPeople())
                .account_num(payer.getAccountNum())
                .payer(payer.getName())
                .bank(payer.getBank())
                .receiptUrl(receipt.getImageUrl())
                .build();
    }
}
