package com.dexxy.departmentManager.basic.controller;

import com.dexxy.departmentManager.basic.dto.AddNewDepartmentDTO;
import com.dexxy.departmentManager.basic.dto.DepartmentDTO;
import com.dexxy.departmentManager.basic.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody AddNewDepartmentDTO newDepartmentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.createDepartment(newDepartmentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @Valid @RequestBody AddNewDepartmentDTO newDepartmentDTO) {
        return ResponseEntity.ok(departmentService.updateDepartmentById(id, newDepartmentDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentDTO> patchDepartment(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(departmentService.patchDepartmentById(id, updates));
    }
}