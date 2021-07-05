package com.saptarshi.technohrms.exchanges.auth;

import com.saptarshi.technohrms.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {

    private String username;
    private String password;
    private boolean active;
    private String roles;
    private Employee employee;
}
