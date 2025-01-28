package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.OrderItemDTO;
import com.tj.inventorySpringBoot.entity.Order;
import com.tj.inventorySpringBoot.entity.OrderItem;
import com.tj.inventorySpringBoot.entity.Product;
import com.tj.inventorySpringBoot.repository.OrderItemRepository;
import com.tj.inventorySpringBoot.repository.OrderRepository;
import com.tj.inventorySpringBoot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository; // To fetch the Order entity by ID

    @Autowired
    private ProductRepository productRepository; // To fetch the Product entity by ID

    // Method to create or update an order item
    public OrderItemDTO saveOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = convertToEntity(orderItemDTO);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return convertToDTO(savedOrderItem);
    }

    // Method to get all order items
    public List<OrderItemDTO> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Method to get an order item by its ID
    public OrderItemDTO getOrderItemById(Long id) {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        if (orderItemOptional.isPresent()) {
            return convertToDTO(orderItemOptional.get());
        }
        return null; // You can throw an exception or return a 404 in the controller
    }

    // Method to delete an order item by its ID
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    // Convert OrderItemDTO to OrderItem entity
    private OrderItem convertToEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDTO.getId());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPrice(orderItemDTO.getPrice());

        // Fetch the Order and Product based on IDs from the DTO
        Optional<Order> order = orderRepository.findById(orderItemDTO.getOrderId());
        Optional<Product> product = productRepository.findById(orderItemDTO.getProductId());

        if (order.isPresent() && product.isPresent()) {
            orderItem.setOrder(order.get());
            orderItem.setProduct(product.get());
        }

        return orderItem;
    }

    // Convert OrderItem entity to OrderItemDTO
    private OrderItemDTO convertToDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setPrice(orderItem.getPrice());

        if (orderItem.getOrder() != null) {
            orderItemDTO.setOrderId(orderItem.getOrder().getId());
        }

        if (orderItem.getProduct() != null) {
            orderItemDTO.setProductId(orderItem.getProduct().getId());
        }

        return orderItemDTO;
    }
}

