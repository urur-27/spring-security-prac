package com.sb02.springsecurityprac.dto;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;

public record UserBoardInfo(
    String username,
    List<GrantedAuthority> authorities,
    List<String> boards
) {
}