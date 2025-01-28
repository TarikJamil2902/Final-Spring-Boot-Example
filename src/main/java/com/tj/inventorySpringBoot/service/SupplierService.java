package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.SupplierDTO;
import com.tj.inventorySpringBoot.entity.Supplier;
import com.tj.inventorySpringBoot.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    // Create or update a supplier
    public SupplierDTO saveSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = convertToEntity(supplierDTO);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return convertToDTO(savedSupplier);
    }

    // Get all suppliers
    public List<SupplierDTO> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a supplier by ID
    public SupplierDTO getSupplierById(Long id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        return supplierOptional.map(this::convertToDTO).orElse(null);
    }

    // Delete a supplier by ID
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    // Convert SupplierDTO to Supplier entity
    private Supplier convertToEntity(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        supplier.setId(supplierDTO.getId());
        supplier.setName(supplierDTO.getName());
        supplier.setContact(supplierDTO.getContact());
        supplier.setAddress(supplierDTO.getAddress());
        return supplier;
    }

    // Convert Supplier entity to SupplierDTO
    private SupplierDTO convertToDTO(Supplier supplier) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setId(supplier.getId());
        supplierDTO.setName(supplier.getName());
        supplierDTO.setContact(supplier.getContact());
        supplierDTO.setAddress(supplier.getAddress());
        return supplierDTO;
    }
}

