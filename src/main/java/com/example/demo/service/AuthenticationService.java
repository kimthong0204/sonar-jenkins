package com.example.demo.service;


import com.example.demo.entity.AuthenticationRequest;
import com.example.demo.entity.AuthenticationResponse;
import com.example.demo.entity.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.error.AuthorDuplicateException;
import com.example.demo.error.UserExistException;
import com.example.demo.model.ERole;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerUser(RegisterRequest request) {
        if (repository.existsByUserName(request.getUserName())) {
            throw new UserExistException();
        } else {
            var user = User.builder()
                    .userName(request.getUserName())
                    .userPassword(passwordEncoder.encode(request.getUserPassword()))
                    .role(ERole.USER)
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
    }

    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        if (repository.existsByUserName(request.getUserName())) {
            throw new UserExistException();
        } else if (request.getUserName().equals("admin")) {
            var admin = User.builder()
                    .userName(request.getUserName())
                    .userPassword(passwordEncoder.encode(request.getUserPassword()))
                    .role(ERole.ADMIN)
                    .build();
            repository.save(admin);
            var jwtToken = jwtService.generateToken(admin);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new RuntimeException();
        }
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getUserPassword()
                )
        );
        var user = repository.findByUserName(request.getUserName())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
