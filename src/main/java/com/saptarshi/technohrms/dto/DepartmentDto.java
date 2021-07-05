package com.saptarshi.technohrms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private int id;

    private String name;

    private String description;

    private Integer totalEmployee;
}
