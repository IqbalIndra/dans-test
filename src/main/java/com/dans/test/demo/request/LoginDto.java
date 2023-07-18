package com.dans.test.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotNull(message = "this field required")
    @Length(min = 5, max = 50)
    private String username;

    @NotNull(message = "this field required")
    @Length(min = 5, max = 50)
    private String password;
}
