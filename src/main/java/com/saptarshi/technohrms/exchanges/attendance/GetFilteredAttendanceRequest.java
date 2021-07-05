package com.saptarshi.technohrms.exchanges.attendance;

import com.saptarshi.technohrms.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetFilteredAttendanceRequest {

    private Employee employee;

    private Date stDate;

    private Date endDate;

}
