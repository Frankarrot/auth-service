package com.imp.authservice.clientserver.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {
    private final Boolean isExist;
    private final Boolean isSellerOrAdmin;

    @Builder

    private MemberResponse(final Boolean isExist, final Boolean isSellerOrAdmin) {
        this.isExist = isExist;
        this.isSellerOrAdmin = isSellerOrAdmin;
    }
}
