package org.ewebshop.commons.clients.user;


public record UserDto(String username,
                      String email,
                      String password,
                      String address,
                      String phoneNumber,
                      String role) {}