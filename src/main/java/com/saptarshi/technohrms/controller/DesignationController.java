package com.saptarshi.technohrms.controller;

import com.saptarshi.technohrms.dto.DepartmentDto;
import com.saptarshi.technohrms.dto.DesignationDto;
import com.saptarshi.technohrms.exchanges.department.AddDepartmentRequest;
import com.saptarshi.technohrms.exchanges.designation.AddDesignationRequest;
import com.saptarshi.technohrms.service.DepartmentMgmtService;
import com.saptarshi.technohrms.service.DesignationMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DesignationController {

    @Autowired
    DesignationMgmtService designationMgmtService;

    @RequestMapping(value = "/designation", method = RequestMethod.POST)
    public ResponseEntity<?> addDesignation(@RequestBody AddDesignationRequest request){
        return ResponseEntity.ok(designationMgmtService.addDesignation(request));
    }

    @RequestMapping(value = "/designations", method = RequestMethod.GET)
    public ResponseEntity<?> getDesignations(){
        return ResponseEntity.ok(designationMgmtService.getDesignationList());
    }

    @RequestMapping(value = "/designation", method = RequestMethod.GET)
    public ResponseEntity<?> getDesignation(@RequestParam Integer id){
        DesignationDto designationDto = designationMgmtService.getDesignation(id);
        return ResponseEntity.ok(designationDto);
    }

    @RequestMapping(value = "/designation/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateDesignation(@PathVariable(value = "id") Integer id, @RequestBody DesignationDto designationDto){
        return ResponseEntity.ok(designationMgmtService.updateDesignation(id, designationDto));
    }

}
