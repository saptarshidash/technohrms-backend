package com.saptarshi.technohrms.exchanges.employee;

import com.saptarshi.technohrms.dto.EmployeeDto;
import com.saptarshi.technohrms.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetEmployeeResponse {
   private List<EmployeeDto> employeeList;
}
