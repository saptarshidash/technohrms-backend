package com.saptarshi.technohrms.controller;

import com.saptarshi.technohrms.exchanges.leave.AddLeaveSetupRequest;
import com.saptarshi.technohrms.exchanges.leave.CreateLeaveRequest;
import com.saptarshi.technohrms.exchanges.leave.LeaveApprovalRequest;
import com.saptarshi.technohrms.exchanges.leave.UpdateLeaveRequest;
import com.saptarshi.technohrms.service.LeaveMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "leave-mgmt")
public class LeaveController {

    @Autowired
    private LeaveMgmtService leaveMgmtService;

    @RequestMapping(value = "/leave", method = RequestMethod.POST)
    public ResponseEntity<?> setupLeave(@RequestBody AddLeaveSetupRequest request){
        return ResponseEntity.ok(leaveMgmtService.setupLeave(request));
    }

    @RequestMapping(value = "/leave/employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getLeaveSetupByEmployee(@PathVariable(value = "id") Integer id){
        return ResponseEntity.ok(leaveMgmtService.getLeaveSetup(id));
    }

    @RequestMapping(value ="/leave/employee", method = RequestMethod.GET)
    public ResponseEntity<?> getLeaveBalance(@RequestParam String leaveName, @RequestParam Integer empId){
        return ResponseEntity.ok(leaveMgmtService.getLeaveBalance(leaveName, empId));
    }

    @RequestMapping(value = "/leave/employee/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> createLeaveRequest(@PathVariable("id") int id, @RequestBody CreateLeaveRequest request){
        return ResponseEntity.ok(leaveMgmtService.createLeaveRequest(id, request));
    }

    @RequestMapping(value = "/leave/employee/requests", method = RequestMethod.GET)
    public ResponseEntity<?> getAllLeaveRequest(){
        return ResponseEntity.ok(leaveMgmtService.getAllLeaveRequest());
    }

    @RequestMapping(value = "/leave/employee/request", method = RequestMethod.GET)
    public ResponseEntity<?> getLeaveRequestByEmployee(@RequestParam Integer id){
        return ResponseEntity.ok(leaveMgmtService.getLeaveRequestByEmployee(id));
    }

    @RequestMapping(value = "/leave/employee/request", method = RequestMethod.PATCH)
    public ResponseEntity<?> makeLeaveApproval(@RequestBody LeaveApprovalRequest request){
        return ResponseEntity.ok(leaveMgmtService.makeLeaveApproval(request));
    }

    @RequestMapping(value = "/leave/employee/request/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateLeaveRequest(@PathVariable(value = "id") Integer id,@RequestBody UpdateLeaveRequest request){
        return ResponseEntity.ok(leaveMgmtService.updateLeaveRequest(id, request));
    }

}
