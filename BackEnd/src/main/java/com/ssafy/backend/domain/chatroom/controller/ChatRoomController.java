package com.ssafy.backend.domain.chatroom.controller;

import com.ssafy.backend.domain.chatroom.dto.ChatRoomCreateRequestDto;
import com.ssafy.backend.domain.chatroom.dto.ChatRoomDto;
import com.ssafy.backend.domain.chatroom.entity.Theme;
import com.ssafy.backend.domain.chatroom.service.ChatRoomService;
import com.ssafy.backend.domain.security.utils.JwtUtils;
import com.ssafy.backend.domain.user.model.entity.User;
import com.ssafy.backend.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    private final Random random = new Random();


    // 채팅방 생성
    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")  // 로그인 한 사용자만 접근 가능
    public ResponseEntity<Message<ChatRoomDto>> createRoom(@RequestBody ChatRoomCreateRequestDto chatRoomCreateRequestDto,
                                                           @AuthenticationPrincipal User user) {
        Long userId = user.getId();
        ChatRoomDto result = chatRoomService.createRoom(chatRoomCreateRequestDto, userId);

        return ResponseEntity.ok().body(Message.success(result));
    }


    // 채팅방 전체 목록 조회
    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Message<List<ChatRoomDto>>> findAllChatRooms() {
        List<ChatRoomDto> result = chatRoomService.findAllChatRooms();
        return ResponseEntity.ok().body(Message.success(result));
    }


    // 특정 채팅방 입장 (입장하는 로직으로 service 코드 변경해야함)
    @PutMapping("/{ChatRoomId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Message<ChatRoomDto>> EnterChatRoom(@PathVariable("ChatRoomId") String ChatRoomId,
                                                              @AuthenticationPrincipal User user) {
        Long userId = user.getId();
        ChatRoomDto result = chatRoomService.EnterChatRoom(ChatRoomId, userId);
        return ResponseEntity.ok().body(Message.success(result));
    }


    // 채팅방 나가기
    @PatchMapping("/leave/{ChatRoomId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Message<Void>> LeaveChatRoom(@PathVariable("ChatRoomId") String ChatRoomId,
                                                       @AuthenticationPrincipal User user) {
        Long userId = user.getId();
        chatRoomService.LeaveChatRoom(ChatRoomId, userId);
        return ResponseEntity.ok().body(Message.success());
    }


    @GetMapping("/randomTheme")
    public Theme getRandomTheme() {
        Theme[] themes = Theme.values();
        return themes[random.nextInt(themes.length)];
    }
}