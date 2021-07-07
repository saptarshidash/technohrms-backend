package com.saptarshi.technohrms.exchanges.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@NoArgsConstructor
public class AuthResponse {

    private String jwt;
    private String username;
    private Collection<? extends GrantedAuthority> role;
    private int empId;

    public AuthResponse(String token, String username, Collection<? extends GrantedAuthority> authorities, int empId) {
        this.jwt = token;
        this.username = username;
        this.role = authorities;
        this.empId = empId;
    }
}
