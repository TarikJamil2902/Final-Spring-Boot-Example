package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.ReturnDTO;
import com.tj.inventorySpringBoot.entity.Order;
import com.tj.inventorySpringBoot.entity.PurchaseOrder;
import com.tj.inventorySpringBoot.entity.Return;
import com.tj.inventorySpringBoot.repository.OrderRepository;
import com.tj.inventorySpringBoot.repository.PurchaseOrderRepository;
import com.tj.inventorySpringBoot.repository.ReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReturnService {

    @Autowired
    private ReturnRepository returnRepository;

    @Autowired
    private OrderRepository orderRepository; // To fetch the Order entity by ID
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository; // To fetch the PurchaseOrder entity by ID

    // Create or update a return record
    public ReturnDTO saveReturn(ReturnDTO returnDTO) {
        Return returnEntity = convertToEntity(returnDTO);
        Return savedReturn = returnRepository.save(returnEntity);
        return convertToDTO(savedReturn);
    }

    // Get all returns
    public List<ReturnDTO> getAllReturns() {
        List<Return> returns = returnRepository.findAll();
        return returns.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a return by ID
    public ReturnDTO getReturnById(Long id) {
        Optional<Return> returnOptional = returnRepository.findById(id);
        return returnOptional.map(this::convertToDTO).orElse(null);
    }

    // Delete a return by ID
    public void deleteReturn(Long id) {
        returnRepository.deleteById(id);
    }

    // Convert ReturnDTO to Return entity
    private Return convertToEntity(ReturnDTO returnDTO) {
        Return returnEntity = new Return();
        returnEntity.setId(returnDTO.getId());
        returnEntity.setReason(returnDTO.getReason());
        returnEntity.setQuantity(returnDTO.getQuantity());

        // Set the associated order if it exists
        if (returnDTO.getCustomerOrderId() != null) {
            Order customerOrder = orderRepository.findById(returnDTO.getCustomerOrderId()).orElse(null);
            returnEntity.setCustomerOrder(customerOrder);
        }

        // Set the associated purchase order if it exists
        if (returnDTO.getSupplierReturnId() != null) {
            PurchaseOrder supplierReturn = purchaseOrderRepository.findById(returnDTO.getSupplierReturnId()).orElse(null);
            returnEntity.setSupplierReturn(supplierReturn);
        }

        return returnEntity;
    }

    // Convert Return entity to ReturnDTO
    private ReturnDTO convertToDTO(Return returnEntity) {
        ReturnDTO returnDTO = new ReturnDTO();
        returnDTO.setId(returnEntity.getId());
        returnDTO.setReason(returnEntity.getReason());
        returnDTO.setQuantity(returnEntity.getQuantity());

        // Set the customer order ID if it exists
        if (returnEntity.getCustomerOrder() != null) {
            returnDTO.setCustomerOrderId(returnEntity.getCustomerOrder().getId());
        }

        // Set the supplier return ID if it exists
        if (returnEntity.getSupplierReturn() != null) {
            returnDTO.setSupplierReturnId(returnEntity.getSupplierReturn().getId());
        }

        return returnDTO;
    }
}
