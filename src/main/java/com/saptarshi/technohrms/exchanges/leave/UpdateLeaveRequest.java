package com.saptarshi.technohrms.exchanges.leave;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLeaveRequest {
    private Date startDate;

    private Date endDate;

    private String reason;
}
