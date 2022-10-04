package org.ewebshop.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;
import java.util.Objects;


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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        log.info("new user registration {}", userLoginRequest);
        try {
            return new ResponseEntity<>(userService.login(userLoginRequest), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (LoginException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{username}")
    public void getByUsername(@PathVariable String username) {
        userService.getByUsername(username);
    }

    @GetMapping("/unique/{username}")
    public boolean uniqueCheck(@PathVariable String username) {
        try {
            User user = userService.getByUsername(username);
            return Objects.nonNull(user);
        } catch (EntityNotFoundException exception) {
            return false;
        }
    }
}
