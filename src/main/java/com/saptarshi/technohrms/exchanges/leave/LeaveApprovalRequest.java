package com.saptarshi.technohrms.exchanges.leave;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveApprovalRequest {

    private Integer id;

    private String status;
}
