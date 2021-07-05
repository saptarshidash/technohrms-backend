package com.saptarshi.technohrms.repository.designation;

import com.saptarshi.technohrms.dto.DepartmentDto;
import com.saptarshi.technohrms.dto.DesignationDto;
import com.saptarshi.technohrms.entity.Department;
import com.saptarshi.technohrms.entity.Designation;
import com.saptarshi.technohrms.exchanges.department.AddDepartmentResponse;
import com.saptarshi.technohrms.exchanges.department.GetDepartmentResponse;
import com.saptarshi.technohrms.exchanges.designation.AddDesignationRequest;
import com.saptarshi.technohrms.exchanges.designation.AddDesignationResponse;
import com.saptarshi.technohrms.exchanges.designation.GetDesignationResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DesignationRepositoryServiceImpl implements DesignationRepositoryService{

    @Autowired
    DesignationRepository designationRepository;

    @Override
    public AddDesignationResponse addDesignation(AddDesignationRequest request) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        Designation designation = mapper.map(request, Designation.class);
        designation = designationRepository.save(designation);

        AddDesignationResponse response = mapper.map(designation, AddDesignationResponse.class);
        return response;
    }

    @Override
    public GetDesignationResponse getAllDesignation() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        List<Designation> designationList = designationRepository.findAll();
        List<DesignationDto> designationDtoList = new ArrayList<>();

        for(Designation designation: designationList){
            DesignationDto designationDto = mapper.map(designation, DesignationDto.class);
            designationDtoList.add(designationDto);
        }

        return new GetDesignationResponse(designationDtoList);
    }

    @Override
    public DesignationDto getDesignation(Integer id) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Designation designation;

        try {
            designation = designationRepository.findDesignationById(id).get();

        }catch (NoSuchElementException e){
            e.printStackTrace();
            return null;
        }

        DesignationDto designationDto = mapper.map(designation, DesignationDto.class);
        return designationDto;
    }

    @Override
    public DesignationDto updateDesignation(Integer id, DesignationDto designationDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        Designation designation;
        try {
            designation = designationRepository.findDesignationById(id).get();
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return null;
        }
        designation.setName(designationDto.getName());
        designation.setDescription(designationDto.getDescription());
        designation = designationRepository.save(designation);

        designationDto = mapper.map(designation, DesignationDto.class);
        return designationDto;
    }
}
