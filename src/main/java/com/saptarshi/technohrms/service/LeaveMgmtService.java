package com.saptarshi.technohrms.service;

import com.saptarshi.technohrms.entity.Employee;
import com.saptarshi.technohrms.exchanges.leave.*;
import com.saptarshi.technohrms.repository.leave.LeaveRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LeaveMgmtService {

    @Autowired
    private LeaveRepositoryService repositoryService;

    public AddLeaveSetupResponse setupLeave(AddLeaveSetupRequest request){

        return repositoryService.setupLeave(request);
    }

    public GetLeaveSetupResponse getLeaveSetup(Integer id){
        return repositoryService.getLeaveSetup(id);
    }

    public Map<String , Integer> getLeaveBalance(String name, Integer id){
        return repositoryService.getLeaveBalance(name, id);
    }

    public CreateLeaveResponse createLeaveRequest(CreateLeaveRequest request){
        return repositoryService.createLeaveRequest(request);
    }

    public GetLeaveRequestResponse getAllLeaveRequest(){
        return repositoryService.getAllLeaveRequest();
    }

    public GetLeaveRequestResponse getLeaveRequestByEmployee(Integer id){
        return repositoryService.getLeaveRequestByEmployee(id);
    }

    public LeaveApprovalResponse makeLeaveApproval(LeaveApprovalRequest request){
        return repositoryService.makeLeaveApproval(request);
    }

}
