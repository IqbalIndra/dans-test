package com.dans.test.demo.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginResponse {
    private String username;
    private String accessToken;
}
