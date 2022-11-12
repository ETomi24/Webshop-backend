package org.ewebshop.auth;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ewebshop.commons.clients.user.UserClient;
import org.ewebshop.commons.clients.user.UserDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserClient userClient;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userClient.getByUsername(username);
        if (user.username() != null){
            log.info("User found in the database: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.role()));
            return new org.springframework.security.core.userdetails.User(user.username(), user.password(), authorities);
        } else {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }
    }
}
