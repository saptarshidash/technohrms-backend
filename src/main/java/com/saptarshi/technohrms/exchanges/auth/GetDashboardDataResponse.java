package com.saptarshi.technohrms.exchanges.auth;

import com.saptarshi.technohrms.dto.LeaveRequestDto;
import com.saptarshi.technohrms.entity.TrainingEmployee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GetDashboardDataResponse {

    private int totalEmployee;

    private int totalDepartment;

    private int todayAttendance;

    private List<LeaveRequestDto> pendingLeaveList;

    List<TrainingEmployee> onGoingTrainings;

}
