package com.saptarshi.technohrms.repository.designation;

import com.saptarshi.technohrms.entity.Department;
import com.saptarshi.technohrms.entity.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Integer> {
    Optional<Designation> findDesignationById(Integer id);

    Optional<Department> findDesignationByName(String name);

    boolean existsByName(String name);
}
