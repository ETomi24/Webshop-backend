package org.ewebshop.user.dto;

public record UserUpdateRequest(
        String username,
        String email,
        String phoneNumber,
        String address
) {
}
