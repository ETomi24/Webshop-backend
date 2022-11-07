package org.ewebshop.user.dto;

public record UserLoginRequest(
        String username,
        String password
) {
}
