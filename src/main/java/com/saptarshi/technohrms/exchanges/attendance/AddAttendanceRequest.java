package com.saptarshi.technohrms.exchanges.attendance;

import com.saptarshi.technohrms.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAttendanceRequest {

    private Employee employee;

    private Date date;

    private Long inTime;

    private Long outTime;
}
