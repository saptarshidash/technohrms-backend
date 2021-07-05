package com.saptarshi.technohrms.exchanges.leave;

import com.saptarshi.technohrms.dto.LeaveSetupDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetLeaveSetupResponse {

    private List<LeaveSetupDto> leaveSetupDtoList;
}
