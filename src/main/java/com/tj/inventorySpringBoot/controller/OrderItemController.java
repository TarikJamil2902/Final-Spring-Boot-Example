package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.dto.OrderItemDTO;
import com.tj.inventorySpringBoot.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    // Endpoint to create or update an order item
    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrUpdateOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        OrderItemDTO savedOrderItem = orderItemService.saveOrderItem(orderItemDTO);
        return new ResponseEntity<>(savedOrderItem, HttpStatus.CREATED);
    }

    // Endpoint to get all order items
    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        List<OrderItemDTO> orderItems = orderItemService.getAllOrderItems();
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    // Endpoint to get an order item by its ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Long id) {
        OrderItemDTO orderItemDTO = orderItemService.getOrderItemById(id);
        if (orderItemDTO != null) {
            return new ResponseEntity<>(orderItemDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to delete an order item by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

