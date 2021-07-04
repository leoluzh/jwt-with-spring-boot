package com.lamdbasys.jwt.spring.security.controller;

import com.lamdbasys.jwt.spring.security.exception.InvalidUsernameOrPasswordException;
import com.lamdbasys.jwt.spring.security.model.AuthenticationRequest;
import com.lamdbasys.jwt.spring.security.model.AuthenticationResponse;
import com.lamdbasys.jwt.spring.security.service.CustomUserDetailsService;
import com.lamdbasys.jwt.spring.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/api/v1/security")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController implements Serializable {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody final AuthenticationRequest authenticationRequest) throws Exception {

        try {
            final var upatk = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            final var authentication = this.authenticationManager.authenticate(upatk);
        } catch (AuthenticationException ex) {
            throw new InvalidUsernameOrPasswordException("Invalid username or password");
        }

        final var userDetails = this.customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final var token = this.jwtService.generateToken(userDetails);

        return ResponseEntity.ok(AuthenticationResponse.builder().jwt(token).build());
    }

}
