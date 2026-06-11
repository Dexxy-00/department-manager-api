package com.dexxy.departmentManager.basic.service;

import com.dexxy.departmentManager.basic.dto.AddNewDepartmentDTO;
import com.dexxy.departmentManager.basic.dto.DepartmentDTO;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<DepartmentDTO> getAllDepartments();

    DepartmentDTO getDepartmentById(Long id);

    DepartmentDTO createDepartment(AddNewDepartmentDTO newDepartmentDTO);

    Void deleteDepartmentById(Long id);

    DepartmentDTO updateDepartmentById(Long id, AddNewDepartmentDTO newDepartmentDTO);

    DepartmentDTO patchDepartmentById(Long id, Map<String, Object> updates);

    List<DepartmentDTO> getDepartmentsByTitle(String title);
}
