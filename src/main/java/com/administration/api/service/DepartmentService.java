package com.administration.api.service;

import com.administration.api.model.Department;
import com.administration.api.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepo departmentRepo;

    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepo.findById(id);
    }

    public Department addDepartment(Department department) {
        return departmentRepo.save(department);
    }

    public Optional<Department> updateDepartment(Long id, Department departmentRequest) {
        Optional<Department> department = departmentRepo.findById(id);
        if (department.isPresent()) {
            department.get().setName(departmentRequest.getName());
            departmentRepo.save(department.get());
        }
        return department;
    }

    public void deleteDepartment(Long id) {
        Optional<Department> department = departmentRepo.findById(id);
        department.ifPresent(departments -> departmentRepo.delete(departments));
    }
}
