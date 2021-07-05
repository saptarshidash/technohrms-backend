package com.saptarshi.technohrms.service;

import com.saptarshi.technohrms.dto.DepartmentDto;
import com.saptarshi.technohrms.dto.DesignationDto;
import com.saptarshi.technohrms.exchanges.department.AddDepartmentResponse;
import com.saptarshi.technohrms.exchanges.designation.AddDesignationRequest;
import com.saptarshi.technohrms.exchanges.designation.AddDesignationResponse;
import com.saptarshi.technohrms.exchanges.designation.GetDesignationResponse;
import com.saptarshi.technohrms.repository.designation.DesignationRepository;
import com.saptarshi.technohrms.repository.designation.DesignationRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DesignationMgmtService {

    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private DesignationRepositoryService repositoryService;

    public AddDesignationResponse addDesignation(AddDesignationRequest request){
        String message = "";

        if(designationRepository.existsByName(request.getName())){
            message = message + "Designation already exists";
            AddDesignationResponse response = new AddDesignationResponse(
                    request.getName(),
                    message
            );
            return response;
        }

        AddDesignationResponse response = repositoryService.addDesignation(request);
        response.setMessage("Designation added successfully");
        return response;
    }

    public GetDesignationResponse getDesignationList(){
        return repositoryService.getAllDesignation();
    }

    public DesignationDto getDesignation(Integer id){
        DesignationDto designationDto =  repositoryService.getDesignation(id);
        return designationDto;
    }

    public DesignationDto updateDesignation(Integer id, DesignationDto designationDto){
        return repositoryService.updateDesignation(id, designationDto);
    }


}
