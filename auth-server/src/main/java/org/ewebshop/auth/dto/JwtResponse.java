package org.ewebshop.auth.dto;

public record JwtResponse(
        String token,
        String username,
        String role
) { }
