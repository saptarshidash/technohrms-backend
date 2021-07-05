package com.saptarshi.technohrms.repository.employee;

import com.saptarshi.technohrms.dto.EmployeeDto;
import com.saptarshi.technohrms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByEmail(String email);

    Optional<Employee> findEmployeeById(Integer id);
}
