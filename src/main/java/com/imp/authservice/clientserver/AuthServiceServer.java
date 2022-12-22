package com.imp.authservice.clientserver;

import com.imp.authservice.application.MemberService;
import com.imp.authservice.clientserver.dto.MemberResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth-service/members")
@RestController
public class AuthServiceServer {
    private final MemberService memberService;

    public AuthServiceServer(final MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> find(@PathVariable Long id) {
        final MemberResponse memberResponse = memberService.findForClient(id);
        return ResponseEntity.ok(memberResponse);
    }
}
