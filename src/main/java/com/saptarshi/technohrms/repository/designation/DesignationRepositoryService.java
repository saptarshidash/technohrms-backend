package com.saptarshi.technohrms.repository.designation;

import com.saptarshi.technohrms.dto.DesignationDto;
import com.saptarshi.technohrms.exchanges.designation.AddDesignationRequest;
import com.saptarshi.technohrms.exchanges.designation.AddDesignationResponse;
import com.saptarshi.technohrms.exchanges.designation.GetDesignationResponse;

public interface DesignationRepositoryService {
    AddDesignationResponse addDesignation(AddDesignationRequest request);

    GetDesignationResponse getAllDesignation();

    DesignationDto getDesignation(Integer id);

    DesignationDto updateDesignation(Integer id, DesignationDto designationDto);
}
