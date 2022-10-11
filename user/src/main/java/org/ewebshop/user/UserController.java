package org.ewebshop.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ewebshop.clients.user.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        log.info("new user registration {}", userRegistrationRequest);
        userService.register(userRegistrationRequest);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        log.info("new login {}", userLoginRequest);
        try {
            return new ResponseEntity<>(userService.login(userLoginRequest), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (LoginException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{username}")
    public ResponseEntity<Object> getByUsername(@PathVariable String username) {
        try {
            User user = userService.getByUsername(username);
            return new ResponseEntity<>(new UserDto(user.getUsername(),user.getEmail(),user.getPassword(),user.getAddress(),user.getPhoneNumber(),user.getRole().name()), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/exists/{username}")
    public boolean uniqueCheck(@PathVariable String username) {
        try {
            return userService.userExists(username);
        } catch (Exception exception) {
            return false;
        }
    }
}
