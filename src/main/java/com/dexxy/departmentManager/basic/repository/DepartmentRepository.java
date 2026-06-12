package com.dexxy.departmentManager.basic.repository;

import com.dexxy.departmentManager.basic.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    List<DepartmentEntity> findByTitle(String title);

    List<DepartmentEntity> findByIsActive(Boolean isActive);
}