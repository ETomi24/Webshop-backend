package org.ewebshop.auth;

public record LoginRequest(
        String username,
        String password
) {
}