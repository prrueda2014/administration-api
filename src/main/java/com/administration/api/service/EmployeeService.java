package com.administration.api.service;

import com.administration.api.model.Employee;
import com.administration.api.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees() { return employeeRepo.findAll(); }

    public Optional<Employee> getEmployeeById(Long id) { return employeeRepo.findById(id); }

    public Employee addEmployee(Employee employee) { return employeeRepo.save(employee); }

    public Optional<Employee> updateEmployee(Long id, Employee requestEmployee) {
        Optional<Employee> currentEmployee = employeeRepo.findById(id);
        if(currentEmployee.isPresent()) {
            currentEmployee.get().setName(requestEmployee.getName());
            currentEmployee.get().setEmail(requestEmployee.getEmail());
            currentEmployee.get().setTitle(requestEmployee.getTitle());
            currentEmployee.get().setSalary(requestEmployee.getSalary());
            currentEmployee.get().setDepartment(requestEmployee.getDepartment());
            employeeRepo.save(currentEmployee.get());
        }
        return currentEmployee;
    }

    public void deleteEmployee(Long id, Employee requestEmployee) {
        Optional<Employee> currentEmployee = employeeRepo.findById(id);
        currentEmployee.ifPresent(employee -> employeeRepo.delete(employee));
    }
}
