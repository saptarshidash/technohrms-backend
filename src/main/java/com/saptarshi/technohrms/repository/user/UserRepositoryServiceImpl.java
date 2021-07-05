package com.saptarshi.technohrms.repository.user;

import com.saptarshi.technohrms.dto.LeaveRequestDto;
import com.saptarshi.technohrms.entity.Employee;
import com.saptarshi.technohrms.entity.TrainingEmployee;
import com.saptarshi.technohrms.entity.User;
import com.saptarshi.technohrms.exchanges.auth.GetDashboardDataResponse;
import com.saptarshi.technohrms.exchanges.auth.UserRegistrationRequest;
import com.saptarshi.technohrms.exchanges.auth.UserRegistrationResponse;
import com.saptarshi.technohrms.repository.attendance.AttendanceRepository;
import com.saptarshi.technohrms.repository.department.DepartmentRepository;
import com.saptarshi.technohrms.repository.employee.EmployeeRepository;
import com.saptarshi.technohrms.repository.leave.LeaveRepository;
import com.saptarshi.technohrms.repository.leave.LeaveRepositoryService;
import com.saptarshi.technohrms.repository.training.TrainingEmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    LeaveRepositoryService leaveRepository;

    @Autowired
    TrainingEmployeeRepository trainingEmployeeRepository;

    // Overriden method to create a new user with credentials by using existing employee
    @Override
    public UserRegistrationResponse createUser(UserRegistrationRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        User user = modelMapper.map(request, User.class);
        user.setActive(true);
        user = userRepository.save(user);
        UserRegistrationResponse response = modelMapper.map(user, UserRegistrationResponse.class);
        return response;
    }

    @Override
    public GetDashboardDataResponse getDashboardData() {



        int totalEmp = employeeRepository.findAll().size();

        int totalDept =   departmentRepository.findAll().size();

        Date current = new Date(System.currentTimeMillis());

        int attendance = attendanceRepository.findAttendanceByDateIsBetween(current, current).size();

        List<LeaveRequestDto> leaveRequestDtoList = leaveRepository.getAllLeaveRequest().getLeaveRequestDtoList();

        List<LeaveRequestDto> pendingLeaveList = new ArrayList<>();

        for(LeaveRequestDto request: leaveRequestDtoList){
            if(request.getStatus().equals("PENDING")){
                pendingLeaveList.add(request);
            }
        }

        List<TrainingEmployee> onGoingTrainings = trainingEmployeeRepository.findAllByCompletionStatus(false);

        return new GetDashboardDataResponse(
                totalEmp,
                totalDept,
                attendance,
                pendingLeaveList,
                onGoingTrainings
        );
    }
}
