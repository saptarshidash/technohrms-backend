package com.saptarshi.technohrms.repository.user;

import com.saptarshi.technohrms.exchanges.auth.GetDashboardDataResponse;
import com.saptarshi.technohrms.exchanges.auth.UserRegistrationRequest;
import com.saptarshi.technohrms.exchanges.auth.UserRegistrationResponse;

public interface UserRepositoryService {

    public UserRegistrationResponse createUser(UserRegistrationRequest request);

    GetDashboardDataResponse getDashboardData();

}
