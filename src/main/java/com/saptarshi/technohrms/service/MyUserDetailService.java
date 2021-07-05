package com.saptarshi.technohrms.service;

import com.saptarshi.technohrms.config.MyUserDetails;
import com.saptarshi.technohrms.entity.User;
import com.saptarshi.technohrms.exchanges.auth.GetDashboardDataResponse;
import com.saptarshi.technohrms.exchanges.auth.UserRegistrationRequest;
import com.saptarshi.technohrms.exchanges.auth.UserRegistrationResponse;
import com.saptarshi.technohrms.repository.user.UserRepository;
import com.saptarshi.technohrms.repository.user.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositoryService userRepositoryService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return user.map(MyUserDetails::new).get();
    }

    public UserRegistrationResponse createUser(UserRegistrationRequest request){

        String message = "";

        if(request.getEmployee()!= null && userRepository.existsByEmployee(request.getEmployee())){
            message = message + "Credentials already exists for this user";
        }

        if(userRepository.existsByUsername(request.getUsername())){
            message = message + "Username already exists";
        }

        if(!message.isEmpty()){
            UserRegistrationResponse response = new UserRegistrationResponse(
                    request.getUsername(),
                    request.getRoles(),
                    message
            );
            return response;
        }

        UserRegistrationResponse response = userRepositoryService.createUser(request);
        response.setMessage("Registration Successfull");
        return response;
    }

    public GetDashboardDataResponse getDashboardDataResponse(){
        return userRepositoryService.getDashboardData();
    }
}
