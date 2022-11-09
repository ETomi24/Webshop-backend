package org.ewebshop.auth;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ewebshop.auth.dto.JwtResponse;
import org.ewebshop.auth.dto.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping(value = "/login")
    public JwtResponse createAuthenticationToken(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.username());
            String token = jwtTokenUtil.generateToken(userDetails);
            String role = jwtTokenUtil.getRoleFromToken(token).getAuthority();
            return new JwtResponse(token,userDetails.getUsername(), role);
        } catch (UsernameNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage());
        } catch (AuthenticationException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        }
    }

    @GetMapping(value = "/role/{token}")
    public SimpleGrantedAuthority getRoleFromToken(@PathVariable String token) {
        try {
            return jwtTokenUtil.getRoleFromToken(token);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        }
    }
}
