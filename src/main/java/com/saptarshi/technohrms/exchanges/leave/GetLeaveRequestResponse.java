package com.saptarshi.technohrms.exchanges.leave;

import com.saptarshi.technohrms.dto.LeaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetLeaveRequestResponse {

    private List<LeaveRequestDto> leaveRequestDtoList;
}
