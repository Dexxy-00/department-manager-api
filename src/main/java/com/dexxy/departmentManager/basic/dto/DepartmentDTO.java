package com.dexxy.departmentManager.basic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;

    private String title;

    private Boolean isActive;

    private LocalDateTime createdAt;
}
