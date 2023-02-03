package com.nikitalipatov.houseapp.auth;

import com.nikitalipatov.houseapp.config.JwtService;
import com.nikitalipatov.houseapp.models.Role;
import com.nikitalipatov.houseapp.models.Users;
import com.nikitalipatov.houseapp.repos.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsersRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    record RegisteredUser(int id, String name, int age, String email) {}

    public RegisteredUser register(RegisterRequest request) {
        var user = Users.builder()
                .name(request.getName())
                .age(request.getAge())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new RegisteredUser(user.getId(), user.getName(), user.getAge(), user.getEmail());
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}