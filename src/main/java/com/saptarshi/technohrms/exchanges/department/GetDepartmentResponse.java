package com.saptarshi.technohrms.exchanges.department;

import com.saptarshi.technohrms.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetDepartmentResponse {
    private List<DepartmentDto> departmentDtoList;
}
