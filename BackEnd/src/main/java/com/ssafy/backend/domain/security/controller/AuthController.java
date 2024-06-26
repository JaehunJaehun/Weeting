package com.ssafy.backend.domain.security.controller;

import com.ssafy.backend.domain.security.dto.LoginRequest;
import com.ssafy.backend.domain.security.dto.LoginResponse;
import com.ssafy.backend.domain.security.dto.RefreshRequest;
import com.ssafy.backend.domain.security.service.AuthService;
import com.ssafy.backend.domain.user.model.entity.User;
import com.ssafy.backend.global.utils.MessageUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/user")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<MessageUtils> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest.getAccount(), loginRequest.getPassword());
        return ResponseEntity.ok().body(MessageUtils.success(loginResponse));
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageUtils> logout( @RequestHeader("Authorization") String authorizationHeader) {
        // "Bearer " 접두사를 제거하고 토큰만 추출합니다.
        String accessToken = authorizationHeader.replace("Bearer ", "").trim();

        // authService에서 AccessToken을 전달하여 로그아웃 처리
        authService.logout(accessToken);

        return ResponseEntity.ok().body(MessageUtils.success());
    }

}
