package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.CustomerDTO;
import com.tj.inventorySpringBoot.entity.Customer;
import com.tj.inventorySpringBoot.entity.User;
import com.tj.inventorySpringBoot.repository.CustomerRepository;
import com.tj.inventorySpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;  // To handle the relationship with User entity

    // Method to create or update a customer
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }

    // Method to get all customers
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Method to get a customer by its ID
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            return convertToDTO(customerOptional.get());
        }
        return null;  // Handle this as a 404 in the controller or throw an exception
    }

    // Method to delete a customer by its ID
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // Convert CustomerDTO to Customer entity
    private Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setAddress(customerDTO.getAddress());

        // Fetch the User who created this customer by ID
        Optional<User> createdByUser = userRepository.findById(customerDTO.getCreatedByUserId());
        createdByUser.ifPresent(customer::setCreatedBy);  // Set the createdBy relationship if the user is found

        return customer;
    }

    // Convert Customer entity to CustomerDTO
    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setAddress(customer.getAddress());

        // Include the ID of the user who created this customer record
        if (customer.getCreatedBy() != null) {
            customerDTO.setCreatedByUserId(customer.getCreatedBy().getId());
        }

        return customerDTO;
    }
}

