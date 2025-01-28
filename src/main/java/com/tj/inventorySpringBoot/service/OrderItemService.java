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
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // Method to create a new order item
    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = convertToEntity(orderItemDTO);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return convertToDTO(savedOrderItem);
    }

    // Method to update an existing order item
    public OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO) {
        Optional<OrderItem> existingOrderItem = orderItemRepository.findById(id);
        if (existingOrderItem.isPresent()) {
            OrderItem orderItem = existingOrderItem.get();
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItem.setPrice(orderItemDTO.getPrice());

            // Update Order and Product if they exist
            Optional<Order> order = orderRepository.findById(orderItemDTO.getOrderId());
            Optional<Product> product = productRepository.findById(orderItemDTO.getProductId());

            if (order.isPresent()) {
                orderItem.setOrder(order.get());
            }
            if (product.isPresent()) {
                orderItem.setProduct(product.get());
            }

            OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
            return convertToDTO(updatedOrderItem);
        }
        return null; // Or throw exception
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
        return null; // Or throw exception or return 404 in controller
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

        // Fetch Order and Product
        Optional<Order> order = orderRepository.findById(orderItemDTO.getOrderId());
        Optional<Product> product = productRepository.findById(orderItemDTO.getProductId());

        order.ifPresent(orderItem::setOrder);
        product.ifPresent(orderItem::setProduct);

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
