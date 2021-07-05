package com.saptarshi.technohrms.repository.attendance;

import com.saptarshi.technohrms.entity.Attendance;
import com.saptarshi.technohrms.exchanges.attendance.AddAttendanceRequest;
import com.saptarshi.technohrms.exchanges.attendance.AddAttendanceResponse;
import com.saptarshi.technohrms.exchanges.attendance.GetFilteredAttendanceRequest;

import java.sql.Date;
import java.util.*;

public interface AttendanceRepositoryService {

    AddAttendanceResponse addAttendance(AddAttendanceRequest request);

    List<Attendance> getAllAttendanceByDateRange(Date start, Date end);

    List<Attendance> getAllAttendanceByEmployeeAndDateRange(Integer id, Date start, Date end);

}
