package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.ShipmentDTO;
import com.tj.inventorySpringBoot.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    // Endpoint to create or update a shipment
    @PostMapping
    public ResponseEntity<ShipmentDTO> createOrUpdateShipment(@RequestBody ShipmentDTO shipmentDTO) {
        ShipmentDTO savedShipment = shipmentService.saveShipment(shipmentDTO);
        return new ResponseEntity<>(savedShipment, HttpStatus.CREATED);
    }

    // Endpoint to get all shipments
    @GetMapping
    public ResponseEntity<List<ShipmentDTO>> getAllShipments() {
        List<ShipmentDTO> shipments = shipmentService.getAllShipments();
        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }

    // Endpoint to get a shipment by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ShipmentDTO> getShipmentById(@PathVariable Long id) {
        ShipmentDTO shipmentDTO = shipmentService.getShipmentById(id);
        if (shipmentDTO != null) {
            return new ResponseEntity<>(shipmentDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if not found
    }

    // Endpoint to delete a shipment by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Successfully deleted
    }
}

