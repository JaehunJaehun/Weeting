package com.ssafy.backend.domain.chatroom.controller;

import com.ssafy.backend.domain.chatroom.dto.ChatRoomCreateRequestDto;
import com.ssafy.backend.domain.chatroom.dto.ChatRoomDto;
import com.ssafy.backend.domain.chatroom.entity.Theme;
import com.ssafy.backend.domain.chatroom.service.ChatRoomService;
import com.ssafy.backend.domain.user.model.entity.User;
import com.ssafy.backend.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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
                                                           @AuthenticationPrincipal User user) throws Exception {
        ChatRoomDto result = chatRoomService.createRoom(chatRoomCreateRequestDto, user);

        return ResponseEntity.ok().body(Message.success(result));
    }

    // 채팅방 전체 목록 조회 - HTTP(GET)
//    @GetMapping("/all")
//    @PreAuthorize("isAuthenticated()")
//    public ResponseEntity<Message<?>> findAllChatRooms() {
//        List<ChatRoomDto> result = chatRoomService.findAllChatRooms();
//        if (result.isEmpty()) {
//            String noChatRoomsMessage = "생성된 방이 없습니다.";
//            return ResponseEntity.ok().body(Message.success(noChatRoomsMessage));
//        } else {
//            return ResponseEntity.ok().body(Message.success(result));
//        }
//    }


    // 특정 채팅방 입장
    @PatchMapping("/{chatRoomId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Message<ChatRoomDto>> EnterChatRoom(@PathVariable("chatRoomId") String chatRoomId,
                                                              @AuthenticationPrincipal User user) {
        ChatRoomDto result = chatRoomService.EnterChatRoom(chatRoomId, user);

        return ResponseEntity.ok().body(Message.success(result));
    }


    // 채팅방 나가기
    @PatchMapping("/leave/{chatRoomId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Message<String>> LeaveChatRoom(@PathVariable("chatRoomId") String chatRoomId,
                                                       @AuthenticationPrincipal User user) {
        chatRoomService.LeaveChatRoom(chatRoomId, user);
        String result = "나가기 완료 !";

        return ResponseEntity.ok().body(Message.success(result));
    }


    // 빠른 입장
    @PatchMapping("/fastenter")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Message<ChatRoomDto>> fastEnter(@AuthenticationPrincipal User user) {
        String selectedRoomId = chatRoomService.fastEnter(user);
        ChatRoomDto result = chatRoomService.EnterChatRoom(selectedRoomId, user);
        return ResponseEntity.ok().body(Message.success(result));
    }


    // 방 정보 조회 (실시간) - 실패
//    @MessageMapping("/api/v1/chatroom/get/{roomId}")  // /app/api/v1/chatroom/get/{roomId} (요청)
//    @SendTo("/topic/room/{roomId}")  // topic/room/{roomId} (구독)
//    public ChatRoomDto findChatRoom(@DestinationVariable String roomId) {
//
//        ChatRoomDto result = chatRoomService.findChatRoom(roomId);
//
//        return result;
//    }

    // 모든 방 전체 조회 (실시간) - 실패
//    @MessageMapping("/api/v1/chatroom/get/all")  // /app/api/v1/chatroom/get/all (요청)
//    @SendTo("/topic/roomList")  // /topic/roomList (구독)
//    public List<ChatRoomDto> findAllChatRooms() {
//
//        List<ChatRoomDto> result = chatRoomService.findAllChatRooms();
//
//        return result;
//    }




    @GetMapping("/randomTheme")
    public Theme getRandomTheme() {
        Theme[] themes = Theme.values();
        return themes[random.nextInt(themes.length)];
    }

    @GetMapping("/randomNumbers/{num}")
    public ResponseEntity<Message<List<Integer>>> getRandomNumbers(@PathVariable int num) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return ResponseEntity.ok().body(Message.success(numbers));
    }
}