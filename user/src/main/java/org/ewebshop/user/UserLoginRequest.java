package org.ewebshop.user;

public record UserLoginRequest(
        String username,
        String password
) {
}
