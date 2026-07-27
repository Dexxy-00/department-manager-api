package com.dexxy.departmentManager.basic.controller;

import com.dexxy.departmentManager.basic.dto.AddNewDepartmentDTO;
import com.dexxy.departmentManager.basic.dto.DepartmentDTO;
import com.dexxy.departmentManager.basic.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<Page<DepartmentDTO>> getDepartments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return ResponseEntity.ok(departmentService.getAllDepartments(pageable));
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