package com.saptarshi.technohrms.controller;

import com.saptarshi.technohrms.exchanges.attendance.AddAttendanceRequest;
import com.saptarshi.technohrms.exchanges.attendance.GetFilteredAttendanceRequest;
import com.saptarshi.technohrms.service.AttendanceMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
public class AttendanceController {

    @Autowired
    private AttendanceMgmtService attendanceMgmtService;

    @RequestMapping(value = "/attendance", method = RequestMethod.POST)
    public ResponseEntity<?> addAttendance(@RequestBody AddAttendanceRequest request){
        return ResponseEntity.ok(attendanceMgmtService.addAttendance(request));
    }

    @RequestMapping(value = "/attendances", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAttendanceByDateRange(@RequestParam Date start, @RequestParam Date end){
        return ResponseEntity.ok(attendanceMgmtService.getAllAttendanceByDateRange(start, end));
    }

    @RequestMapping(value = "/attendances/employee", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAttendanceByEmployeeAndDateRange(@RequestParam Integer id, @RequestParam Date start, @RequestParam Date end){
        return ResponseEntity.ok(attendanceMgmtService.getAllAttendanceByEmployeeAndDateRange(id, start, end));
    }



}
