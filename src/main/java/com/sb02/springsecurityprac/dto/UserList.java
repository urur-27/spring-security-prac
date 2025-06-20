package com.sb02.springsecurityprac.dto;

import java.util.List;

public record UserList(
    List<UserRole> users
) {
}