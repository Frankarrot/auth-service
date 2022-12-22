package com.imp.authservice.application;

import com.imp.authservice.application.dto.MemberCreateRequest;
import com.imp.authservice.application.dto.MemberFindResponse;
import com.imp.authservice.application.dto.MemberLoginRequest;
import com.imp.authservice.clientserver.dto.MemberResponse;
import com.imp.authservice.domain.Member;
import com.imp.authservice.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    public MemberService(final MemberRepository memberRepository, final SessionManager sessionManager) {
        this.memberRepository = memberRepository;
        this.sessionManager = sessionManager;
    }

    @Transactional
    public long signUp(final MemberCreateRequest request) {
        final Member member = request.toEntity();
        final Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    public MemberFindResponse find(final Long id) {
        final Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("member not found by id"));

        return MemberFindResponse.from(member);
    }

    public long login(final MemberLoginRequest memberLoginRequest) {
        final Member foundMember = memberRepository.findByEmail(memberLoginRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("member not found by email"));
        foundMember.validatePassword(memberLoginRequest.getPassword());
        sessionManager.login(foundMember.getId());
        return foundMember.getId();
    }

    public MemberResponse findForClient(final Long id) {
        final boolean exists = memberRepository.existsById(id);
        final Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("member not found by email"));

        return MemberResponse
                .builder()
                .isExist(exists)
                .isSellerOrAdmin(member.isSellerOrAdmin())
                .build();
    }
}
