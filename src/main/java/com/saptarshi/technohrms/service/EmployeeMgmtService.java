package com.saptarshi.technohrms.service;

import com.saptarshi.technohrms.dto.EmployeeDto;
import com.saptarshi.technohrms.exchanges.employee.EmployeeAddRequest;
import com.saptarshi.technohrms.exchanges.employee.EmployeeAddResponse;
import com.saptarshi.technohrms.exchanges.employee.GetEmployeeResponse;
import com.saptarshi.technohrms.repository.employee.EmployeeRepository;
import com.saptarshi.technohrms.repository.employee.EmployeeRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMgmtService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeRepositoryService employeeRepositoryService;

    public EmployeeAddResponse addEmployee(EmployeeAddRequest request){

        String message = "";

        if(employeeRepository.existsByEmail(request.getEmail())){
            message = message + "Employee with this email already exists";
        }

        if(!message.isEmpty()){
            EmployeeAddResponse response = new EmployeeAddResponse(
                    request.getName(),
                    request.getEmail(),
                    message
            );

            return response;
        }

        EmployeeAddResponse response = employeeRepositoryService.addEmployee(request);
        response.setMessage("Employee added successfully");
        return response;
    }

    public GetEmployeeResponse getEmployeeList(){
       return employeeRepositoryService.getEmployees();
    }

    public EmployeeDto getEmployee(Integer id){
        EmployeeDto employeeDto =  employeeRepositoryService.getEmployee(id);
        return employeeDto;
    }

    public EmployeeDto updateEmployee(Integer id, EmployeeDto employeeDto){
        return employeeRepositoryService.updateEmployee(id, employeeDto);
    }
}
