package com.dexxy.departmentManager.basic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AddNewDepartmentDTO {

    @NotBlank
    @Size(max = 50, min = 5)
    private String title;

    @NotNull
    private Boolean isActive;

    @NotNull
    @Past
    private LocalDateTime createdAt;
}