package com.imp.authservice.domain;

import com.imp.authservice.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;
    private String nickname;
    private String password;
    private long point;

    public Member(final Role role, final String email, final String nickname, final String password) {
        this(null, role, email, nickname, password, 0L);
    }

    public Member(final Long id, final Role role, final String email, final String nickname, final String password,
                  final long point) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.point = point;
    }

    public void validatePassword(final String password) {
        if (this.password.equals(password)) {
            return;
        }
        throw new IllegalArgumentException("not validate password");
    }

    public boolean isSellerOrAdmin() {
        return role == Role.SELLER || role == Role.ADMIN;
    }
}
