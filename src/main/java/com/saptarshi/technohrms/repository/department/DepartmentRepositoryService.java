package com.saptarshi.technohrms.repository.department;

import com.saptarshi.technohrms.dto.DepartmentDto;
import com.saptarshi.technohrms.dto.EmployeeDto;
import com.saptarshi.technohrms.exchanges.department.AddDepartmentRequest;
import com.saptarshi.technohrms.exchanges.department.AddDepartmentResponse;
import com.saptarshi.technohrms.exchanges.department.GetDepartmentResponse;

public interface DepartmentRepositoryService {

    AddDepartmentResponse addDepartment(AddDepartmentRequest request);

    GetDepartmentResponse getAllDepartment();

    DepartmentDto getDepartment(Integer id);

    DepartmentDto updateDepartment(Integer id, DepartmentDto departmentDto);
}
