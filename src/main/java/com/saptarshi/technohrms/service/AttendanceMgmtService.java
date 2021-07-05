package com.saptarshi.technohrms.service;

import com.saptarshi.technohrms.entity.Attendance;
import com.saptarshi.technohrms.exchanges.attendance.AddAttendanceRequest;
import com.saptarshi.technohrms.exchanges.attendance.AddAttendanceResponse;
import com.saptarshi.technohrms.exchanges.attendance.GetFilteredAttendanceRequest;
import com.saptarshi.technohrms.repository.attendance.AttendanceRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;
@Service
public class AttendanceMgmtService {


   @Autowired
   private AttendanceRepositoryService attendanceRepositoryService;

    public AddAttendanceResponse addAttendance(AddAttendanceRequest request){
       return attendanceRepositoryService.addAttendance(request);

    }

    public List<Attendance> getAllAttendanceByDateRange(Date start, Date end){
        return attendanceRepositoryService.getAllAttendanceByDateRange(start, end);
    }

    public List<Attendance> getAllAttendanceByEmployeeAndDateRange(Integer id, java.sql.Date start, Date end){
        return attendanceRepositoryService.getAllAttendanceByEmployeeAndDateRange(id, start, end);
    }
}
