package org.ewebshop.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ewebshop.commons.clients.user.UserDto;
import org.ewebshop.user.dto.UserRegistrationRequest;
import org.ewebshop.user.dto.UserUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        try{
            log.info("new user registration {}", userRegistrationRequest);
            userService.register(userRegistrationRequest);
        } catch (EntityExistsException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/{username}")
    public UserDto getByUsername(@PathVariable String username) {
        try {
            User user = userService.getByUsername(username);
            return new UserDto(user.getUsername(),user.getEmail(),user.getPassword(),user.getAddress(),user.getPhoneNumber(),user.getRole().name());
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PatchMapping("/update/{username}")
    public void updateUser(@PathVariable String username, @RequestBody UserUpdateRequest userUpdateRequest) {
        try {
            userService.updateUser(username, userUpdateRequest);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/exists/{username}")
    public boolean existsCheck(@PathVariable String username) {
        try {
            return userService.userExists(username);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
