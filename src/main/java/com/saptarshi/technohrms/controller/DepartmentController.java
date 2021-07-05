package com.saptarshi.technohrms.controller;

import com.saptarshi.technohrms.dto.DepartmentDto;
import com.saptarshi.technohrms.dto.EmployeeDto;
import com.saptarshi.technohrms.entity.Department;
import com.saptarshi.technohrms.exchanges.department.AddDepartmentRequest;
import com.saptarshi.technohrms.service.DepartmentMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentMgmtService departmentMgmtService;

    @RequestMapping(value = "/department", method = RequestMethod.POST)
    public ResponseEntity<?> addDepartment(@RequestBody AddDepartmentRequest request){
        return ResponseEntity.ok(departmentMgmtService.addDepartment(request));
    }

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public ResponseEntity<?> getDepartments(){
        return ResponseEntity.ok(departmentMgmtService.getDepartmentList());
    }

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public ResponseEntity<?> getDepartment(@RequestParam Integer id){
        DepartmentDto departmentDto = departmentMgmtService.getDepartment(id);
        return ResponseEntity.ok(departmentDto);
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateDepartment(@PathVariable(value = "id") Integer id, @RequestBody DepartmentDto departmentDto){
        return ResponseEntity.ok(departmentMgmtService.updateDepartment(id, departmentDto));
    }
}
