package org.ewebshop.user;

import org.ewebshop.user.dto.UserLoginRequest;
import org.ewebshop.user.dto.UserRegistrationRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserRegistrationRequest userRegistrationRequest) throws EntityExistsException{
        if (userExists(userRegistrationRequest.username())){
            throw new EntityExistsException("User exits");
        } else {
            User user = User.builder()
                    .email(userRegistrationRequest.email())
                    .username(userRegistrationRequest.username())
                    .phoneNumber(userRegistrationRequest.phoneNumber())
                    .password(passwordEncoder.encode(userRegistrationRequest.password()))
                    .address(userRegistrationRequest.address())
                    .role(userRegistrationRequest.role())
                    .build();
            userRepository.save(user);
        }
    }

    public String login(UserLoginRequest userLoginRequest) throws EntityNotFoundException, LoginException {
        User user = getByUsername(userLoginRequest.username());
        if (passwordEncoder.matches(userLoginRequest.password(),user.getPassword())) {
            return "token";
        } else {
            throw new LoginException("Bad password");
        }
    }


    public User getByUsername(String username) throws EntityNotFoundException{
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            return user.get();
        } else {
            throw new EntityNotFoundException("User not found");
        }
    }

    public boolean userExists(String username){
        return userRepository.existsById(username);
    }

}
