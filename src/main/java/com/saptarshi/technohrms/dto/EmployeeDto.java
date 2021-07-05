package com.saptarshi.technohrms.dto;

import com.saptarshi.technohrms.entity.Department;
import com.saptarshi.technohrms.entity.Designation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private int id;
    private String name;
    private Department department;
    private Designation designation;
    private String email;
    private String mobile;
    private Date joiningDate;
    private Date dob;
    private String workType;
}
