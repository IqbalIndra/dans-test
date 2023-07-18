package com.dans.test.demo.controller;

import com.dans.test.demo.model.User;
import com.dans.test.demo.request.LoginDto;
import com.dans.test.demo.response.LoginResponse;
import com.dans.test.demo.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword())
            );
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            LoginResponse response = new LoginResponse(user.getUsername(), accessToken);

            return ResponseEntity.ok().body(response);
        }catch (BadCredentialsException ex) {
            final Map<String, Object> body = new HashMap<>();
            body.put("error", "Bad Credentials");
            body.put("message", "Invalid Username or Password !");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
        }
    }
}
