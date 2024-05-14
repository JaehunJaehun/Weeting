package com.ssafy.backend.domain.chatroom.controller;

import com.ssafy.backend.domain.chatroom.dto.ChatRoomGameResultDto;
import com.ssafy.backend.domain.chatroom.service.ChatRoomGameService;
import com.ssafy.backend.domain.user.model.entity.User;
import com.ssafy.backend.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatroom/game")
public class ChatRoomGameController {

    private final ChatRoomGameService chatRoomGameService;



    // 방 상태 변경
    @PatchMapping("/status/{chatRoomId}")
    public ResponseEntity<Message<LocalTime>> roomStatusModify(@PathVariable("chatRoomId") String chatRoomId) {
        LocalTime result = chatRoomGameService.roomStatusModify(chatRoomId);

        return ResponseEntity.ok().body(Message.success(result));
    }


    // ready 상태 변경
    @PatchMapping("/ready/{chatRoomId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Message<String>> readyStatusTrans(@PathVariable("chatRoomId") String chatRoomId,
                                                              @AuthenticationPrincipal User user) {
        chatRoomGameService.readyStatusTrans(chatRoomId, user);
        String result = "준비 상태 변경 완료 !";

        return ResponseEntity.ok().body(Message.success(result));
    }


    // 죽었을 때 isAlive값 변경
    @PatchMapping("/dead/{chatRoomId}")
    public ResponseEntity<Message<String>> grilledChicken(@PathVariable("chatRoomId") String chatRoomId,
                                                            @AuthenticationPrincipal User user) {
        String result = chatRoomGameService.grilledChicken(chatRoomId, user);

        return ResponseEntity.ok().body(Message.success(result));
    }


    // 금지어 설정
    @PatchMapping("/wordsetting/{chatRoomId}")
    public void forbiddenWordSetting(@PathVariable("chatRoomId") String chatRoomId,
                                     @AuthenticationPrincipal User user,
                                     @RequestBody String word) {
        chatRoomGameService.forbiddenWordSetting(chatRoomId, user, word);
    }


    // 게임 결과
    @GetMapping("/result/{chatRoomId}")
    public ResponseEntity<Message<List<ChatRoomGameResultDto>>> gameResult(@PathVariable("chatRoomId") String chatRoomId) {
        List<ChatRoomGameResultDto> result = chatRoomGameService.gameResult(chatRoomId);

        return ResponseEntity.ok().body(Message.success(result));
    }


    // 게임 결과 초기화
    @PatchMapping("/initialize/{chatRoomId}")
    public ResponseEntity<Message<String>> gameInitialize (@PathVariable("chatRoomId") String chatRoomId) {
        chatRoomGameService.gameInitialize(chatRoomId);
        String result = "유저 상태 초기화 완료 !";

        return ResponseEntity.ok().body(Message.success(result));
    }

}
