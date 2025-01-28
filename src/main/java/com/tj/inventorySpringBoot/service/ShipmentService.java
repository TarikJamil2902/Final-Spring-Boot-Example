package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.ShipmentDTO;
import com.tj.inventorySpringBoot.entity.Order;
import com.tj.inventorySpringBoot.entity.Shipment;
import com.tj.inventorySpringBoot.repository.OrderRepository;
import com.tj.inventorySpringBoot.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private OrderRepository orderRepository; // To fetch the associated Order by its ID

    // Create or update a shipment
    public ShipmentDTO saveShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = convertToEntity(shipmentDTO);
        Shipment savedShipment = shipmentRepository.save(shipment);
        return convertToDTO(savedShipment);
    }

    // Get all shipments
    public List<ShipmentDTO> getAllShipments() {
        List<Shipment> shipments = shipmentRepository.findAll();
        return shipments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a shipment by its ID
    public ShipmentDTO getShipmentById(Long id) {
        Optional<Shipment> shipmentOptional = shipmentRepository.findById(id);
        return shipmentOptional.map(this::convertToDTO).orElse(null);
    }

    // Delete a shipment by ID
    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);
    }

    // Convert ShipmentDTO to Shipment entity
    private Shipment convertToEntity(ShipmentDTO shipmentDTO) {
        Shipment shipment = new Shipment();
        shipment.setId(shipmentDTO.getId());
        shipment.setTrackingNumber(shipmentDTO.getTrackingNumber());
        shipment.setCarrier(shipmentDTO.getCarrier());
        shipment.setShippedDate(shipmentDTO.getShippedDate());
        shipment.setEstimatedDeliveryDate(shipmentDTO.getEstimatedDeliveryDate());

        // Set associated Order entity
        if (shipmentDTO.getOrderId() != null) {
            Order order = orderRepository.findById(shipmentDTO.getOrderId()).orElse(null);
            shipment.setOrder(order);
        }

        return shipment;
    }

    // Convert Shipment entity to ShipmentDTO
    private ShipmentDTO convertToDTO(Shipment shipment) {
        ShipmentDTO shipmentDTO = new ShipmentDTO();
        shipmentDTO.setId(shipment.getId());
        shipmentDTO.setTrackingNumber(shipment.getTrackingNumber());
        shipmentDTO.setCarrier(shipment.getCarrier());
        shipmentDTO.setShippedDate(shipment.getShippedDate());
        shipmentDTO.setEstimatedDeliveryDate(shipment.getEstimatedDeliveryDate());

        // Set associated Order ID if it exists
        if (shipment.getOrder() != null) {
            shipmentDTO.setOrderId(shipment.getOrder().getId());
        }

        return shipmentDTO;
    }
}

