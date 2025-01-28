package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.OrderDTO;
import com.tj.inventorySpringBoot.entity.Order;
import com.tj.inventorySpringBoot.entity.OrderItem;
import com.tj.inventorySpringBoot.enums.OrderStatus;
import com.tj.inventorySpringBoot.repository.OrderItemRepository;
import com.tj.inventorySpringBoot.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository; // Assuming you have an OrderItemRepository to handle order items

    // Method to create or update an order
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    // Method to get all orders
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Method to get an order by its ID
    public OrderDTO getOrderById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return convertToDTO(orderOptional.get());
        }
        return null; // You can throw an exception here or return a 404 status in the controller
    }

    // Method to delete an order by its ID
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // Convert OrderDTO to Order entity
    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerContact(orderDTO.getCustomerContact());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setStatus(OrderStatus.valueOf(orderDTO.getStatus())); // Convert String status to Enum

        // Map the orderItemIds to OrderItem objects (assumes that orderItems exist in DB)
        if (orderDTO.getOrderItemIds() != null) {
            List<OrderItem> orderItems = orderItemRepository.findAllById(orderDTO.getOrderItemIds());
            order.setOrderItems(orderItems);
        }

        return order;
    }

    // Convert Order entity to OrderDTO
    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setCustomerContact(order.getCustomerContact());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setStatus(order.getStatus().name()); // Convert Enum to String

        // Get the order item IDs for the DTO
        List<Long> orderItemIds = order.getOrderItems().stream()
                .map(OrderItem::getId)  // Assuming you have an OrderItem entity with an ID
                .collect(Collectors.toList());
        orderDTO.setOrderItemIds(orderItemIds);

        return orderDTO;
    }
}
