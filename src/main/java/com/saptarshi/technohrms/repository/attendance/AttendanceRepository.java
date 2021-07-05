package com.saptarshi.technohrms.repository.attendance;

import com.saptarshi.technohrms.entity.Attendance;
import com.saptarshi.technohrms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findAttendanceByEmployeeAndDateIsBetween(Employee employee, java.sql.Date stDate, java.sql.Date endDate);

    List<Attendance> findAttendanceByDateIsBetween(java.sql.Date stDate, java.sql.Date endDate);

    Optional<Attendance> findAttendanceByEmployeeAndDate(Employee e, Date d);

    boolean existsAttendanceByDateAndEmployee(Date date, Employee employee);

}
