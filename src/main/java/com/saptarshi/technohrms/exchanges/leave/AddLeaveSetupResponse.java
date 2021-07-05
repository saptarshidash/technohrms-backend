package com.saptarshi.technohrms.exchanges.leave;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddLeaveSetupResponse {

    private String leaveName;

    private int leaveCount;

    private String message;

}
