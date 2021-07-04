package com.lamdbasys.jwt.spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequest implements Serializable {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
