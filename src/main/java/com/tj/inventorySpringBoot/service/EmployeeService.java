package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.EmployeeDTO;
import com.tj.inventorySpringBoot.entity.Employee;
import com.tj.inventorySpringBoot.enums.Role;
import com.tj.inventorySpringBoot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Method to create or update an employee
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    // Method to get all employees
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Method to get an employee by its ID
    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            return convertToDTO(employeeOptional.get());
        }
        return null; // Handle this case as a 404 in the controller or throw an exception
    }

    // Method to delete an employee by its ID
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Convert EmployeeDTO to Employee entity
    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());

        // Set the role from String to Enum
        if (employeeDTO.getRole() != null) {
            employee.setRole(Role.valueOf(employeeDTO.getRole()));
        }

        return employee;
    }

    // Convert Employee entity to EmployeeDTO
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());

        // Set the role as a String representation
        if (employee.getRole() != null) {
            employeeDTO.setRole(employee.getRole().name());
        }

        return employeeDTO;
    }
}

