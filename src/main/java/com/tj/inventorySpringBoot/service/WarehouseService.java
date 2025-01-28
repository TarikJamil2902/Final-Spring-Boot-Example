package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.WarehouseDTO;
import com.tj.inventorySpringBoot.entity.Warehouse;
import com.tj.inventorySpringBoot.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public WarehouseDTO saveWarehouse(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(warehouseDTO.getId());
        warehouse.setName(warehouseDTO.getName());
        warehouse.setLocation(warehouseDTO.getLocation());

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return convertToDTO(savedWarehouse);
    }

    public List<WarehouseDTO> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public WarehouseDTO getWarehouseById(Long id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        return warehouse.map(this::convertToDTO).orElse(null);
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    private WarehouseDTO convertToDTO(Warehouse warehouse) {
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        warehouseDTO.setId(warehouse.getId());
        warehouseDTO.setName(warehouse.getName());
        warehouseDTO.setLocation(warehouse.getLocation());
        return warehouseDTO;
    }
}

