package com.saptarshi.technohrms.repository.department;

import com.saptarshi.technohrms.dto.DepartmentDto;
import com.saptarshi.technohrms.dto.EmployeeDto;
import com.saptarshi.technohrms.entity.Department;
import com.saptarshi.technohrms.entity.Employee;
import com.saptarshi.technohrms.exchanges.department.AddDepartmentRequest;
import com.saptarshi.technohrms.exchanges.department.AddDepartmentResponse;
import com.saptarshi.technohrms.exchanges.department.GetDepartmentResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DepartmentRepositoryServiceImpl implements DepartmentRepositoryService{
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentRepositoryService repositoryService;


    @Override
    public AddDepartmentResponse addDepartment(AddDepartmentRequest request) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        Department department = mapper.map(request, Department.class);
        department = departmentRepository.save(department);

        AddDepartmentResponse response = mapper.map(department, AddDepartmentResponse.class);
        return response;
    }

    @Override
    public GetDepartmentResponse getAllDepartment() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        List<Department> departmentList = departmentRepository.findAll();
        List<DepartmentDto> departmentDtoList = new ArrayList<>();

        for(Department department: departmentList){
            DepartmentDto departmentDto = mapper.map(department, DepartmentDto.class);
            departmentDtoList.add(departmentDto);
        }

        return new GetDepartmentResponse(departmentDtoList);
    }

    @Override
    public DepartmentDto getDepartment(Integer id) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Department department;

        try {
            department = departmentRepository.findById(id).get();

        }catch (NoSuchElementException e){
            e.printStackTrace();
            return null;
        }

        DepartmentDto departmentDto = mapper.map(department, DepartmentDto.class);
        return departmentDto;
    }

    @Override
    public DepartmentDto updateDepartment(Integer id, DepartmentDto departmentDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        Department department;
        try {
            department = departmentRepository.findDepartmentById(id).get();
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return null;
        }
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        department = departmentRepository.save(department);

        departmentDto = mapper.map(department, DepartmentDto.class);
        return departmentDto;
    }
}
