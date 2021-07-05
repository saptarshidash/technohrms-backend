package com.saptarshi.technohrms.repository.employee;

import com.saptarshi.technohrms.dto.EmployeeDto;
import com.saptarshi.technohrms.exchanges.employee.EmployeeAddRequest;
import com.saptarshi.technohrms.exchanges.employee.EmployeeAddResponse;
import com.saptarshi.technohrms.exchanges.employee.GetEmployeeResponse;

public interface EmployeeRepositoryService {
    public EmployeeAddResponse addEmployee(EmployeeAddRequest request);

    public GetEmployeeResponse getEmployees();

    public EmployeeDto getEmployee(Integer id);

    public EmployeeDto updateEmployee(Integer id, EmployeeDto employeeDto);
}
