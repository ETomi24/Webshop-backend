package org.ewebshop.user.dto;

import org.ewebshop.user.Role;

public record UserRegistrationRequest(
        String username,
        String email,
        String phoneNumber,
        String password,
        Role role) {
}
