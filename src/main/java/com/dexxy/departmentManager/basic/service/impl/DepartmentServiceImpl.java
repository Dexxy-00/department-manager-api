package com.dexxy.departmentManager.basic.service.impl;

import com.dexxy.departmentManager.basic.dto.AddNewDepartmentDTO;
import com.dexxy.departmentManager.basic.dto.DepartmentDTO;
import com.dexxy.departmentManager.basic.entity.DepartmentEntity;
import com.dexxy.departmentManager.basic.exception.ResourceNotFoundException;
import com.dexxy.departmentManager.basic.repository.DepartmentRepository;
import com.dexxy.departmentManager.basic.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<DepartmentDTO> getAllDepartments(Pageable pageable) {
        Page<DepartmentEntity> departmentsPage = departmentRepository.findAll(pageable);
        return departmentsPage.map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDTO getDepartmentById(Long id) {
        DepartmentEntity department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with id " + id + " not found"));

        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    @Transactional
    public DepartmentDTO createDepartment(AddNewDepartmentDTO newDepartmentDTO) {
        DepartmentEntity department = modelMapper.map(newDepartmentDTO, DepartmentEntity.class);
        departmentRepository.save(department);

        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    @Transactional
    public Void deleteDepartmentById(Long id) {
        if(!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Department with id " + id + " not found");
        }
        departmentRepository.deleteById(id);

        return null;
    }

    @Override
    @Transactional
    public DepartmentDTO updateDepartmentById(Long id, AddNewDepartmentDTO newDepartmentDTO) {
        DepartmentEntity department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with id " + id + " not found"));

        modelMapper.map(newDepartmentDTO, department);
        departmentRepository.save(department);

        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    @Transactional
    public DepartmentDTO patchDepartmentById(Long id, Map<String, Object> updates) {
        DepartmentEntity department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with id " + id + " not found"));

        updates.forEach((key, value) -> {
            switch(key) {
                case "title":
                    department.setTitle((String) value);
                    break;

                case "isActive":
                    department.setIsActive((Boolean) value);
                    break;

                default:
                    throw new IllegalArgumentException("Invalid key " + key);
            }
        });
        departmentRepository.save(department);
        return modelMapper.map(department, DepartmentDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDTO> getDepartmentsByTitle(String title) {
        List<DepartmentEntity> departments = departmentRepository.findByTitle(title);

        return departments.stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDTO> getDepartmentsByActivityStatus(Boolean isActive) {
        List<DepartmentEntity> departments = departmentRepository.findByIsActive(isActive);

        return departments.stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .toList();
    }
}
