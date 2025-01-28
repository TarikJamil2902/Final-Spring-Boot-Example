package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.TaxDTO;
import com.tj.inventorySpringBoot.entity.Tax;
import com.tj.inventorySpringBoot.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaxService {

    @Autowired
    private TaxRepository taxRepository;

    // Create or update a tax
    public TaxDTO saveTax(TaxDTO taxDTO) {
        Tax tax = convertToEntity(taxDTO);
        Tax savedTax = taxRepository.save(tax);
        return convertToDTO(savedTax);
    }

    // Get all taxes
    public List<TaxDTO> getAllTaxes() {
        List<Tax> taxes = taxRepository.findAll();
        return taxes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a tax by ID
    public TaxDTO getTaxById(Long id) {
        Optional<Tax> taxOptional = taxRepository.findById(id);
        return taxOptional.map(this::convertToDTO).orElse(null);
    }

    // Delete a tax by ID
    public void deleteTax(Long id) {
        taxRepository.deleteById(id);
    }

    // Convert TaxDTO to Tax entity
    private Tax convertToEntity(TaxDTO taxDTO) {
        Tax tax = new Tax();
        tax.setId(taxDTO.getId());
        tax.setName(taxDTO.getName());
        tax.setRate(taxDTO.getRate());
        return tax;
    }

    // Convert Tax entity to TaxDTO
    private TaxDTO convertToDTO(Tax tax) {
        TaxDTO taxDTO = new TaxDTO();
        taxDTO.setId(tax.getId());
        taxDTO.setName(tax.getName());
        taxDTO.setRate(tax.getRate());
        return taxDTO;
    }
}
