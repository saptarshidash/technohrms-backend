package com.saptarshi.technohrms.controller;

import com.saptarshi.technohrms.dto.EmployeeDto;
import com.saptarshi.technohrms.exchanges.employee.EmployeeAddRequest;
import com.saptarshi.technohrms.service.EmployeeMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "employee-mgmt")
public class EmployeeController {

    @Autowired
    EmployeeMgmtService employeeMgmtService;

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeAddRequest request){
        return ResponseEntity.ok(employeeMgmtService.addEmployee(request));
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEmployee(){
        return ResponseEntity.ok(employeeMgmtService.getEmployeeList());
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployee(@RequestParam Integer id){
        EmployeeDto employeeDto = employeeMgmtService.getEmployee(id);
        return ResponseEntity.ok(employeeDto);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") Integer id, @RequestBody EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeMgmtService.updateEmployee(id, employeeDto));
    }
}
