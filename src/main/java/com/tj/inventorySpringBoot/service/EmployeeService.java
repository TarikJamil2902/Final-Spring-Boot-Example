package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.EmployeeDTO;
import com.tj.inventorySpringBoot.entity.Employee;
import com.tj.inventorySpringBoot.entity.Role;
import com.tj.inventorySpringBoot.repository.EmployeeRepository;
import com.tj.inventorySpringBoot.repository.RoleRepository;
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

    @Autowired
    private RoleRepository roleRepository;  // Assuming you have a RoleRepository

    // Method to create a new employee
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    // Method to update an existing employee
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(employeeDTO.getName());
            employee.setEmail(employeeDTO.getEmail());
            employee.setPhone(employeeDTO.getPhone());

            if (employeeDTO.getRole() != null) {
                Optional<Role> roleOptional = roleRepository.findByRoleName(employeeDTO.getRole());  // Fetch role by name
                if (roleOptional.isPresent()) {
                    employee.setRole(roleOptional.get());  // Set the Role entity
                } else {
                    // Optionally, handle the case when the role does not exist
                    // You can throw an exception or set a default role
                }
            }

            employee.setHireDate(employeeDTO.getHireDate());
            employee.setSalary(employeeDTO.getSalary());
            employee.setStatus(employeeDTO.getStatus());

            Employee updatedEmployee = employeeRepository.save(employee);
            return convertToDTO(updatedEmployee);
        }
        return null; // Or throw exception if employee not found
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
        return null; // Or throw exception
    }

    // Method to delete an employee by its ID
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Convert EmployeeDTO to Employee entity
    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());

        // Set the role from String to Role entity
        if (employeeDTO.getRole() != null) {
            Optional<Role> roleOptional = roleRepository.findByRoleName(employeeDTO.getRole());  // Find role by name
            if (roleOptional.isPresent()) {
                employee.setRole(roleOptional.get());
            }
        }

        employee.setHireDate(employeeDTO.getHireDate());
        employee.setSalary(employeeDTO.getSalary());
        employee.setStatus(employeeDTO.getStatus());

        return employee;
    }

    // Convert Employee entity to EmployeeDTO
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhone(employee.getPhone());

        // Set the role as a String representation (role name)
        if (employee.getRole() != null) {
            employeeDTO.setRole(employee.getRole().getRoleName());  // Assuming the Role entity has a `getRoleName()` method
        }

        employeeDTO.setHireDate(employee.getHireDate());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setStatus(employee.getStatus());

        return employeeDTO;
    }
}
