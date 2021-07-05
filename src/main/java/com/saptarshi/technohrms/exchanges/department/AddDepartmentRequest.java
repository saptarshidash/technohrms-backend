package com.saptarshi.technohrms.exchanges.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDepartmentRequest {

    private String name;
    private String description;
}
