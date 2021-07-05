package com.saptarshi.technohrms.dto;

import com.saptarshi.technohrms.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveSetupDto {

    private int id;

    private String leaveName;

    private int totalLeave;

    private int usedLeave;

    private int pendingLeave;

}
