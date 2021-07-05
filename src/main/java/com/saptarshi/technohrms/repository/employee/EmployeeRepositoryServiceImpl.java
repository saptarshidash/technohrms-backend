package com.saptarshi.technohrms.repository.employee;

import com.saptarshi.technohrms.dto.EmployeeDto;
import com.saptarshi.technohrms.entity.Department;
import com.saptarshi.technohrms.entity.Employee;
import com.saptarshi.technohrms.exchanges.employee.EmployeeAddRequest;
import com.saptarshi.technohrms.exchanges.employee.EmployeeAddResponse;
import com.saptarshi.technohrms.exchanges.employee.GetEmployeeResponse;
import com.saptarshi.technohrms.repository.department.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmployeeRepositoryServiceImpl implements EmployeeRepositoryService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public EmployeeAddResponse addEmployee(EmployeeAddRequest request) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        Employee employee = mapper.map(request, Employee.class);
        employee = employeeRepository.save(employee);

        Department department = departmentRepository.findDepartmentById(employee.getDepartment().getId()).get();

        department.setTotalEmployee(department.getTotalEmployee() + 1);

        departmentRepository.save(department);

        EmployeeAddResponse response = mapper.map(employee, EmployeeAddResponse.class);
        return response;

    }

    @Override
    public GetEmployeeResponse getEmployees() {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        List<Employee> employeeList = employeeRepository.findAll();

        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        for(Employee employee: employeeList){
            EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
            employeeDtoList.add(employeeDto);
        }

        return new GetEmployeeResponse(employeeDtoList);

    }

    @Override
    public EmployeeDto getEmployee(Integer id) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Employee employee;

        try {
            employee = employeeRepository.findById(id).get();

        }catch (NoSuchElementException e){
            e.printStackTrace();
            return null;
        }

        EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(Integer id, EmployeeDto employeeDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        Employee employee;
        try {
            employee = employeeRepository.findEmployeeById(id).get();
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return null;
        }
        Integer tempId = employee.getId(); // save id otherwise will be lost while mapping
        employee = mapper.map(employeeDto, Employee.class);
        employee.setId(tempId);
        employee = employeeRepository.save(employee);

        employeeDto = mapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }
}
