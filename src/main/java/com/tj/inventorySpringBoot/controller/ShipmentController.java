package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.ShipmentDTO;
import com.tj.inventorySpringBoot.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    // Create a new shipment
    @PostMapping("/create")
    public ResponseEntity<ShipmentDTO> createShipment(@RequestBody ShipmentDTO shipmentDTO) {
        ShipmentDTO createdShipment = shipmentService.createShipment(shipmentDTO);
        return ResponseEntity.ok(createdShipment);
    }

    // Update an existing shipment
    @PutMapping("/update/{id}")
    public ResponseEntity<ShipmentDTO> updateShipment(@PathVariable Long id, @RequestBody ShipmentDTO shipmentDTO) {
        ShipmentDTO updatedShipment = shipmentService.updateShipment(id, shipmentDTO);
        if (updatedShipment != null) {
            return ResponseEntity.ok(updatedShipment);
        }
        return ResponseEntity.notFound().build();
    }

    // Get all shipments
    @GetMapping
    public ResponseEntity<List<ShipmentDTO>> getAllShipments() {
        List<ShipmentDTO> shipments = shipmentService.getAllShipments();
        return ResponseEntity.ok(shipments);
    }

    // Get a shipment by ID
    @GetMapping("/{id}")
    public ResponseEntity<ShipmentDTO> getShipmentById(@PathVariable Long id) {
        ShipmentDTO shipmentDTO = shipmentService.getShipmentById(id);
        if (shipmentDTO != null) {
            return ResponseEntity.ok(shipmentDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a shipment by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
        return ResponseEntity.noContent().build();
    }
}
