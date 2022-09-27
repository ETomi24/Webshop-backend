package org.ewebshop.user;

public record UserRegistrationRequest(
        String username,
        String email,
        String phoneNumber,
        String password,
        Role role) {
}
