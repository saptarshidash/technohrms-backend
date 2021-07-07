package com.saptarshi.technohrms.repository.attendance;

import com.saptarshi.technohrms.entity.Attendance;
import com.saptarshi.technohrms.entity.Employee;
import com.saptarshi.technohrms.exchanges.attendance.AddAttendanceRequest;
import com.saptarshi.technohrms.exchanges.attendance.AddAttendanceResponse;
import com.saptarshi.technohrms.exchanges.attendance.GetFilteredAttendanceRequest;
import com.saptarshi.technohrms.repository.employee.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class AttendanceRepositoryServiceImpl implements AttendanceRepositoryService{

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public AddAttendanceResponse addAttendance(AddAttendanceRequest request) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        AddAttendanceResponse response;
        Attendance attendance;
        Employee employee = employeeRepository.findEmployeeById(request.getEmployeeId()).get();
        if(attendanceRepository.existsAttendanceByDateAndEmployee(request.getDate(), employee)){
             attendance = attendanceRepository
                    .findAttendanceByEmployeeAndDate(employee, request.getDate()).get();

             if(attendance.getInTime() != null && attendance.getOutTime()!=null){
                 response = new AddAttendanceResponse("Attendance already made for the day");
                 return response;
             }

            attendance.setOutTime(new Time(request.getTime()));
            attendanceRepository.save(attendance);
            response = new AddAttendanceResponse("Attendance saved successfully");
            return response;

        }else{

            attendance = mapper.map(request, Attendance.class);
            attendance.setDate(request.getDate());
            attendance.setInTime(new Time(request.getTime()));
            attendance = attendanceRepository.save(attendance);
            return new AddAttendanceResponse("Attendance added successfully");
        }
    }

    @Override
    public List<Attendance> getAllAttendanceByDateRange(Date start, Date end) {

        List<Attendance> attendanceList = attendanceRepository.findAttendanceByDateIsBetween(start, end);

        return attendanceList;
    }

    @Override
    public List<Attendance> getAllAttendanceByEmployeeAndDateRange(Integer id, Date start, Date end) {

        Employee employee = employeeRepository.findEmployeeById(id).get();

        List<Attendance> attendanceList = attendanceRepository.findAttendanceByEmployeeAndDateIsBetween(employee,start, end);
        return attendanceList;
    }
}
