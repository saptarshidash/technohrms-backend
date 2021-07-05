package com.saptarshi.technohrms.repository.department;

import com.saptarshi.technohrms.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findDepartmentById(Integer id);

    Optional<Department> findDepartmentByName(String name);

    boolean existsByName(String name);

}
