package com.saptarshi.technohrms.repository.leave;

import com.saptarshi.technohrms.dto.LeaveRequestDto;
import com.saptarshi.technohrms.dto.LeaveSetupDto;
import com.saptarshi.technohrms.entity.Employee;
import com.saptarshi.technohrms.entity.LeaveRequest;
import com.saptarshi.technohrms.entity.LeaveSetup;
import com.saptarshi.technohrms.exchanges.leave.*;
import com.saptarshi.technohrms.repository.employee.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class LeaveRepositoryServiceImpl implements LeaveRepositoryService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public AddLeaveSetupResponse setupLeave(AddLeaveSetupRequest request) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        LeaveSetup leaveSetup = mapper.map(request, LeaveSetup.class);

        if (leaveRepository.existsByLeaveNameAndEmployee(request.getLeaveName(),request.getEmployee())){
            AddLeaveSetupResponse response = new AddLeaveSetupResponse(
                    leaveSetup.getLeaveName(),
                    leaveSetup.getTotalLeave(),
                    "This leave already exist for this employee"
            );
            return response;
        }
        leaveSetup = leaveRepository.save(leaveSetup);

        AddLeaveSetupResponse response = new AddLeaveSetupResponse(
                leaveSetup.getLeaveName(),
                leaveSetup.getTotalLeave(),
                "Leave added successfully"
        );

        return response;
    }

    @Override
    public GetLeaveSetupResponse getLeaveSetup(Integer id) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Employee employee = employeeRepository.findById(id).get();

        List<LeaveSetup> leaveSetupList = leaveRepository.findAllByEmployee(employee);

        List<LeaveSetupDto> leaveSetupDtoList = new ArrayList<>();

        for(LeaveSetup leaveSetup : leaveSetupList){
            leaveSetupDtoList.add(mapper.map(leaveSetup, LeaveSetupDto.class));
        }

        return new GetLeaveSetupResponse(leaveSetupDtoList);

    }

    @Override
    public Map<String, Integer> getLeaveBalance(String leaveName, Integer id) {

        Employee employee = employeeRepository.findEmployeeById(id).get();
        LeaveSetup leaveSetup;
        try {
            leaveSetup = leaveRepository.findLeaveSetupByLeaveNameAndEmployee(leaveName, employee).get();
        }catch (NoSuchElementException e){
            e.printStackTrace();
            HashMap<String, Integer> map = new HashMap<>();
            map.put("Balance",null);
            return map;
        }
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Balance", (leaveSetup.getTotalLeave() - leaveSetup.getUsedLeave()));
        return map;
    }

    @Override
    public CreateLeaveResponse createLeaveRequest(int id,CreateLeaveRequest request) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Employee employee = employeeRepository.findEmployeeById(id).get();
        LeaveRequest leaveRequest = mapper.map(request, LeaveRequest.class);
        LeaveSetup leaveSetup;

        try {
            leaveSetup = leaveRepository.findLeaveSetupByLeaveNameAndEmployee(
                    request.getLeaveName(),
                    employee
            ).get();
        }catch (NoSuchElementException e){
            e.printStackTrace();
            CreateLeaveResponse response = mapper.map(leaveRequest, CreateLeaveResponse.class);
            response.setMessage("Unable to create leave request");
            return response;
        }

        int remainingLeave = leaveSetup.getTotalLeave() - leaveSetup.getUsedLeave();
        int remainingPending = leaveSetup.getTotalLeave() - leaveSetup.getPendingLeave();
        LocalDate dateStart = LocalDate.parse(request.getStartDate().toString());
        LocalDate dateEnd = LocalDate.parse(request.getEndDate().toString());
        long totalDays = ChronoUnit.DAYS.between(dateStart, dateEnd);

        if(leaveSetup.getUsedLeave() == leaveSetup.getTotalLeave()){
            CreateLeaveResponse response = mapper.map(leaveRequest, CreateLeaveResponse.class);
            response.setMessage("All leaves used ");
            return response;
        }else if(leaveSetup.getPendingLeave() == leaveSetup.getTotalLeave()){
            CreateLeaveResponse response = mapper.map(leaveRequest, CreateLeaveResponse.class);
            response.setMessage("Reached maximum pending request limit");
            return response;
        }else if (totalDays > remainingLeave) {
            CreateLeaveResponse response = mapper.map(leaveRequest, CreateLeaveResponse.class);
            response.setMessage("Requested days exceed total remaining "+leaveSetup.getLeaveName()+" leave");
            return response;

        }else if(totalDays > leaveSetup.getTotalLeave()) {
            CreateLeaveResponse response = mapper.map(leaveRequest, CreateLeaveResponse.class);
            response.setMessage("Requested days exceed total assigned "+leaveSetup.getLeaveName()+" leave");
            return response;

        }else if(totalDays > remainingPending) {
            CreateLeaveResponse response = mapper.map(leaveRequest, CreateLeaveResponse.class);
            response.setMessage("You can't create request more than "+remainingPending+" days");
            return response;
        }else if(leaveSetup.getPendingLeave() + totalDays > leaveSetup.getTotalLeave() - leaveSetup.getUsedLeave()){
            CreateLeaveResponse response = mapper.map(leaveRequest, CreateLeaveResponse.class);
            int eligible = leaveSetup.getTotalLeave() - leaveSetup.getUsedLeave() - leaveSetup.getPendingLeave();
            response.setMessage("You can now only create "+eligible+" day worth request");
            return response;
        }
            leaveRequest.setStatus("PENDING");
            leaveRequest.setEmployee(employee);
            leaveRequest = leaveRequestRepository.save(leaveRequest);
            leaveSetup.setPendingLeave((int) (leaveSetup.getPendingLeave()+totalDays));
            leaveRepository.save(leaveSetup);
            CreateLeaveResponse response = mapper.map(leaveRequest, CreateLeaveResponse.class);
            response.setMessage("Leave request created");
            return response;

    }

    @Override
    public GetLeaveRequestResponse getAllLeaveRequest() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        List<LeaveRequest> leaveRequestList = leaveRequestRepository.findAll();

        List<LeaveRequestDto> leaveRequestDtoList = new ArrayList<>();

        for(LeaveRequest leaveRequest : leaveRequestList){
            leaveRequestDtoList.add(mapper.map(leaveRequest, LeaveRequestDto.class));
        }

        GetLeaveRequestResponse response = new GetLeaveRequestResponse(leaveRequestDtoList);
        return response;
    }

    @Override
    public GetLeaveRequestResponse getLeaveRequestByEmployee(Integer id) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Employee employee = employeeRepository.findEmployeeById(id).get();

        List<LeaveRequest> leaveRequestList = leaveRequestRepository.findAllByEmployee(employee);

        List<LeaveRequestDto> leaveRequestDtoList = new ArrayList<>();

        for(LeaveRequest leaveRequest : leaveRequestList){
            leaveRequestDtoList.add(mapper.map(leaveRequest, LeaveRequestDto.class));
        }

        return new GetLeaveRequestResponse(leaveRequestDtoList);


    }

    @Override
    public LeaveApprovalResponse makeLeaveApproval(LeaveApprovalRequest request) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        LeaveRequest leaveRequest = leaveRequestRepository.findLeaveRequestById(request.getId()).get();

        if(!leaveRequest.getStatus().equals("PENDING")){
            LeaveApprovalResponse response = mapper.map(request, LeaveApprovalResponse.class);
            response.setMessage("Can't be "+request.getStatus()+" already "+leaveRequest.getStatus());
            return response;
        }

        leaveRequest.setStatus(request.getStatus());
        leaveRequest = leaveRequestRepository.save(leaveRequest);

        LocalDate dateStart = LocalDate.parse(leaveRequest.getStartDate().toString());
        LocalDate dateEnd = LocalDate.parse(leaveRequest.getEndDate().toString());
        long totalDays = ChronoUnit.DAYS.between(dateStart, dateEnd);

        LeaveSetup leaveSetup = leaveRepository.findLeaveSetupByLeaveNameAndEmployee(
                leaveRequest.getLeaveName(),
                leaveRequest.getEmployee()
        ).get();

        if(request.getStatus().equals("APPROVED")){
            leaveSetup.setPendingLeave((int) (leaveSetup.getPendingLeave() - totalDays));
            leaveSetup.setUsedLeave((int) (leaveSetup.getUsedLeave() + totalDays));
        }else if(request.getStatus().equals("REJECTED")){
            leaveSetup.setPendingLeave((int) (leaveSetup.getPendingLeave() - totalDays));
        }

        leaveRepository.save(leaveSetup);



        LeaveApprovalResponse response = mapper.map(leaveRequest, LeaveApprovalResponse.class);
        response.setMessage("Leave "+request.getStatus());
        return response;


    }

    @Override
    public UpdateLeaveResponse updateLeaveRequest(int id, UpdateLeaveRequest request) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        LeaveRequest leaveRequest = leaveRequestRepository.findLeaveRequestById(id).get();

        leaveRequest.setReason(request.getReason());
        leaveRequest.setStartDate(request.getStartDate());
        leaveRequest.setEndDate(request.getEndDate());
        leaveRequestRepository.save(leaveRequest);

        return new UpdateLeaveResponse("Leave updated successfully");

    }
}
