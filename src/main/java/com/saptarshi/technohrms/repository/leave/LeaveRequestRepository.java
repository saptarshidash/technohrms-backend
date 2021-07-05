package com.saptarshi.technohrms.repository.leave;

import com.saptarshi.technohrms.entity.Employee;
import com.saptarshi.technohrms.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;


public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {

    List<LeaveRequest> findAllByEmployee(Employee employee);

    Optional<LeaveRequest> findLeaveRequestById(Integer id);

}
