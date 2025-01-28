package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.DiscountDTO;
import com.tj.inventorySpringBoot.entity.Discount;
import com.tj.inventorySpringBoot.entity.Order;
import com.tj.inventorySpringBoot.repository.DiscountRepository;
import com.tj.inventorySpringBoot.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private OrderRepository orderRepository; // To handle the relationship with Order entities

    // Method to create or update a discount
    public DiscountDTO saveDiscount(DiscountDTO discountDTO) {
        Discount discount = convertToEntity(discountDTO);
        Discount savedDiscount = discountRepository.save(discount);
        return convertToDTO(savedDiscount);
    }

    // Method to get all discounts
    public List<DiscountDTO> getAllDiscounts() {
        List<Discount> discounts = discountRepository.findAll();
        return discounts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Method to get a discount by its ID
    public DiscountDTO getDiscountById(Long id) {
        Optional<Discount> discountOptional = discountRepository.findById(id);
        if (discountOptional.isPresent()) {
            return convertToDTO(discountOptional.get());
        }
        return null;  // You can throw an exception here or handle it as a 404 in the controller
    }

    // Method to delete a discount by its ID
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }

    // Convert DiscountDTO to Discount entity
    private Discount convertToEntity(DiscountDTO discountDTO) {
        Discount discount = new Discount();
        discount.setId(discountDTO.getId());
        discount.setName(discountDTO.getName());
        discount.setPercentage(discountDTO.getPercentage());
        discount.setStartDate(discountDTO.getStartDate());
        discount.setEndDate(discountDTO.getEndDate());

        // Fetch the related orders by their IDs
        if (discountDTO.getOrderIds() != null) {
            List<Order> orders = orderRepository.findAllById(discountDTO.getOrderIds());
            discount.setOrders(orders);
        }

        return discount;
    }

    // Convert Discount entity to DiscountDTO
    private DiscountDTO convertToDTO(Discount discount) {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setId(discount.getId());
        discountDTO.setName(discount.getName());
        discountDTO.setPercentage(discount.getPercentage());
        discountDTO.setStartDate(discount.getStartDate());
        discountDTO.setEndDate(discount.getEndDate());

        // Include the order IDs in the DTO
        List<Long> orderIds = discount.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList());
        discountDTO.setOrderIds(orderIds);

        return discountDTO;
    }
}

