package org.ewebshop.user;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserRegistrationRequest userRegistrationRequest) {
        User user = User.builder()
                .email(userRegistrationRequest.email())
                .username(userRegistrationRequest.username())
                .phoneNumber(userRegistrationRequest.phoneNumber())
                .password(userRegistrationRequest.password())
                .role(userRegistrationRequest.role())
                .build();
        userRepository.save(user);
    }

    public String login(UserLoginRequest userLoginRequest) throws EntityNotFoundException, LoginException {
        User user = getByUsername(userLoginRequest.username());
        if (user.getPassword().equals(userLoginRequest.password())) {
            return "token";
        } else {
            throw new LoginException("Hibás jelszó");
        }
    }


    public User getByUsername(String username) throws EntityNotFoundException{
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            return user.get();
        } else {
            throw new EntityNotFoundException("Nincs ilyen user");
        }

    }

}
