package com.saptarshi.technohrms.exchanges.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponse {
    private String username;
    private String roles;
    private String message;
}
