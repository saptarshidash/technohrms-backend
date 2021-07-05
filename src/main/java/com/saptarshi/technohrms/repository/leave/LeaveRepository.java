package com.saptarshi.technohrms.repository.leave;

import com.saptarshi.technohrms.dto.LeaveSetupDto;
import com.saptarshi.technohrms.entity.Employee;
import com.saptarshi.technohrms.entity.LeaveSetup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface LeaveRepository extends JpaRepository<LeaveSetup, Integer> {

    boolean existsByLeaveNameAndEmployee(String name, Employee employee);

    List<LeaveSetup> findAllByEmployee(Employee employee);

    Optional<LeaveSetup> findLeaveSetupByLeaveNameAndEmployee(String name, Employee employee);

}
