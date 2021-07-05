package com.saptarshi.technohrms.exchanges.leave;

import com.saptarshi.technohrms.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddLeaveSetupRequest {

    private Employee employee;

    private int totalLeave;

    private String leaveName;

}
