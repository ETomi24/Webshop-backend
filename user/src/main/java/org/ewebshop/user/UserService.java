package org.ewebshop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserRegistrationRequest userRegistrationRequest) {
        User user = User.builder()
                .email(userRegistrationRequest.email())
                .username(userRegistrationRequest.username())
                .phoneNumber(userRegistrationRequest.phoneNumber())
                .password(userRegistrationRequest.password())
                .role(userRegistrationRequest.role())
                .build();
        userRepository.saveAndFlush(user);
    }

    public User getByUsername(String username) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            return user.get();
        } else {
            throw new EntityNotFoundException("Nincs ilyen user");
        }

    }

}
