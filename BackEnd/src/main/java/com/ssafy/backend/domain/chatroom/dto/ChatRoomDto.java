package com.ssafy.backend.domain.chatroom.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ChatRoomDto {

    /*
    * 방 상태 (대기중, 진행중)
    * */
    public enum RoomStatus {
        WAIT, PROGRESS
    }

    private String roomId;  // 채팅방 고유값
    private String name;  // 방 제목
    private String password;  // 비밀번호
    private int maxMembers;  // 참여가능 최대 유저 수
    private List<Long> members;  // 참여 유저 id 목록
    private String theme;  // 대화 주제
    private RoomStatus status;  // 방 상태

}
