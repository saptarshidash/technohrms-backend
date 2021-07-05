package com.saptarshi.technohrms.exchanges.designation;

import com.saptarshi.technohrms.dto.DesignationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetDesignationResponse {
    private List<DesignationDto> designationDtoList;
}
