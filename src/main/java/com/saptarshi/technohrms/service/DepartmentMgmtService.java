package com.saptarshi.technohrms.service;

import com.saptarshi.technohrms.dto.DepartmentDto;
import com.saptarshi.technohrms.dto.EmployeeDto;
import com.saptarshi.technohrms.entity.Department;
import com.saptarshi.technohrms.exchanges.department.AddDepartmentRequest;
import com.saptarshi.technohrms.exchanges.department.AddDepartmentResponse;
import com.saptarshi.technohrms.exchanges.department.GetDepartmentResponse;
import com.saptarshi.technohrms.repository.department.DepartmentRepository;
import com.saptarshi.technohrms.repository.department.DepartmentRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentMgmtService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DepartmentRepositoryService repositoryService;

    public AddDepartmentResponse addDepartment(AddDepartmentRequest request){
        String message = "";

        if(departmentRepository.existsByName(request.getName())){
            message = message + "Department already exists";
            AddDepartmentResponse response = new AddDepartmentResponse(
                    request.getName(),
                    message
            );
            return response;
        }

        AddDepartmentResponse response = repositoryService.addDepartment(request);
        response.setMessage("Department added successfully");
        return response;
    }

    public GetDepartmentResponse getDepartmentList(){
        return repositoryService.getAllDepartment();
    }

    public DepartmentDto getDepartment(Integer id){
        DepartmentDto employeeDto =  repositoryService.getDepartment(id);
        return employeeDto;
    }

    public DepartmentDto updateDepartment(Integer id, DepartmentDto departmentDto){
        return repositoryService.updateDepartment(id, departmentDto);
    }
}
