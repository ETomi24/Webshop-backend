package org.ewebshop.auth.dto;

public record LoginRequest(
        String username,
        String password
) {
}