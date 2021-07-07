package com.saptarshi.technohrms.repository.leave;

import com.saptarshi.technohrms.dto.LeaveRequestDto;
import com.saptarshi.technohrms.entity.Employee;
import com.saptarshi.technohrms.exchanges.leave.*;

import java.util.Map;

public interface LeaveRepositoryService {

     AddLeaveSetupResponse setupLeave(AddLeaveSetupRequest request);

     GetLeaveSetupResponse getLeaveSetup(Integer id);

     Map<String , Integer> getLeaveBalance(String leaveName, Integer id);

     CreateLeaveResponse createLeaveRequest(int empId, CreateLeaveRequest request);

     GetLeaveRequestResponse getAllLeaveRequest();

     GetLeaveRequestResponse getLeaveRequestByEmployee(Integer id);

     LeaveApprovalResponse makeLeaveApproval(LeaveApprovalRequest request);

     UpdateLeaveResponse updateLeaveRequest(int id, UpdateLeaveRequest request);






}
