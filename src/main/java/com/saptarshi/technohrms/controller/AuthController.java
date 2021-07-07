package com.saptarshi.technohrms.controller;

import com.saptarshi.technohrms.entity.User;
import com.saptarshi.technohrms.exchanges.auth.AuthRequest;
import com.saptarshi.technohrms.exchanges.auth.AuthResponse;
import com.saptarshi.technohrms.exchanges.auth.UserRegistrationRequest;
import com.saptarshi.technohrms.repository.user.UserRepository;
import com.saptarshi.technohrms.service.EmployeeMgmtService;
import com.saptarshi.technohrms.service.MyUserDetailService;
import com.saptarshi.technohrms.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    EmployeeMgmtService employeeMgmtService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) throws Exception {

        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("Bad credential");
        }

        // generate token
        UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());

        String token = jwtUtil.generateToken(userDetails);

        User user = userRepository.findByUsername(userDetails.getUsername()).get();

        return ResponseEntity.ok(new AuthResponse(token, userDetails.getUsername(), userDetails.getAuthorities(),
                user.getEmployee().getId()));

    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request){
        return ResponseEntity.ok(userDetailService.createUser(request));
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ResponseEntity<?> getDashboard(){
        return ResponseEntity.ok(userDetailService.getDashboardDataResponse());
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ResponseEntity<?> home(){
        return ResponseEntity.ok("Hi");
    }
}
