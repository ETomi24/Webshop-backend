package org.ewebshop.user;

import org.ewebshop.user.dto.UserLoginRequest;
import org.ewebshop.user.dto.UserRegistrationRequest;
import org.ewebshop.user.dto.UserUpdateRequest;
import org.ewebshop.user.exception.IdNotMatchingException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;
import java.util.Objects;
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

    public void updateUser(String username, UserUpdateRequest userUpdateRequest) throws IdNotMatchingException, EntityNotFoundException {
        if (!Objects.equals(username, userUpdateRequest.username())){throw new IdNotMatchingException();}
        User user = getByUsername(username);
        user.setAddress(userUpdateRequest.address());
        user.setEmail(userUpdateRequest.email());
        user.setPhoneNumber(userUpdateRequest.phoneNumber());
        userRepository.save(user);
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
