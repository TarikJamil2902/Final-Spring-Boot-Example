package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.CustomerDTO;
import com.tj.inventorySpringBoot.entity.Customer;
import com.tj.inventorySpringBoot.repository.CustomerRepository;
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

    // Method to create a new customer
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }

    // Method to update an existing customer
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setEmail(customerDTO.getEmail());
            customer.setPhone(customerDTO.getPhone());
            customer.setBillingAddress(customerDTO.getBillingAddress());
            customer.setShippingAddress(customerDTO.getShippingAddress());
            customer.setLoyaltyPoints(customerDTO.getLoyaltyPoints());
            customer.setCustomerType(customerDTO.getCustomerType());
            customer.setStatus(customerDTO.getStatus());

            Customer updatedCustomer = customerRepository.save(customer);
            return convertToDTO(updatedCustomer);
        }
        return null;  // Handle this as a 404 in the controller or throw an exception
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
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Customer not found with ID: " + id);
        }
    }

    // Convert CustomerDTO to Customer entity
    private Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setBillingAddress(customerDTO.getBillingAddress());
        customer.setShippingAddress(customerDTO.getShippingAddress());
        customer.setLoyaltyPoints(customerDTO.getLoyaltyPoints());
        customer.setCustomerType(customerDTO.getCustomerType());
        customer.setStatus(customerDTO.getStatus());

        return customer;
    }

    // Convert Customer entity to CustomerDTO
    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setBillingAddress(customer.getBillingAddress());
        customerDTO.setShippingAddress(customer.getShippingAddress());
        customerDTO.setLoyaltyPoints(customer.getLoyaltyPoints());
        customerDTO.setCustomerType(customer.getCustomerType());
        customerDTO.setStatus(customer.getStatus());

        return customerDTO;
    }
}
