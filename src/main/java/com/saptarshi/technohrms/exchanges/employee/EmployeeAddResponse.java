package com.saptarshi.technohrms.exchanges.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAddResponse {

    private String name;
    private String email;
    private String message;

}
