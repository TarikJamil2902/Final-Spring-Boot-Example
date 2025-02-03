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

    // Method to create a new discount
    public DiscountDTO createDiscount(DiscountDTO discountDTO) {
        Discount discount = convertToEntity(discountDTO);
        Discount savedDiscount = discountRepository.save(discount);
        return convertToDTO(savedDiscount);
    }

    // Method to update an existing discount
    public DiscountDTO updateDiscount(Long id, DiscountDTO discountDTO) {
        Optional<Discount> discountOptional = discountRepository.findById(id);
        if (discountOptional.isPresent()) {
            Discount discount = discountOptional.get();
            discount.setDiscountCode(discountDTO.getDiscountCode());
            discount.setDescription(discountDTO.getDescription());
            discount.setDiscountType(discountDTO.getDiscountType());
            discount.setAmount(discountDTO.getAmount());
            discount.setValidFrom(discountDTO.getValidFrom());
            discount.setValidUntil(discountDTO.getValidUntil());
            discount.setStatus(discountDTO.getStatus());
            discount.setApplicableTo(discountDTO.getApplicableTo());

            // Update the related orders if present
//            if (discountDTO.getOrderIds() != null) {
//                List<Order> orders = orderRepository.findAllById(discountDTO.getOrderIds());
//                discount.setOrders(orders);
//            }

            Discount updatedDiscount = discountRepository.save(discount);
            return convertToDTO(updatedDiscount);
        }
        return null;  // Return null or handle as a 404 response in the controller
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
        return null;  // Handle as 404 or throw exception in controller
    }

    // Method to delete a discount by its ID
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }

    // Convert DiscountDTO to Discount entity
    private Discount convertToEntity(DiscountDTO discountDTO) {
        Discount discount = new Discount();
        discount.setDiscountId(discountDTO.getDiscountId());
        discount.setDiscountCode(discountDTO.getDiscountCode());
        discount.setDescription(discountDTO.getDescription());
        discount.setDiscountType(discountDTO.getDiscountType());
        discount.setAmount(discountDTO.getAmount());
        discount.setValidFrom(discountDTO.getValidFrom());
        discount.setValidUntil(discountDTO.getValidUntil());
        discount.setStatus(discountDTO.getStatus());
        discount.setApplicableTo(discountDTO.getApplicableTo());

        // Fetch the related orders by their IDs if present
//        if (discountDTO.getOrderIds() != null) {
//            List<Order> orders = orderRepository.findAllById(discountDTO.getOrderIds());
//            discount.setOrders(orders);
//        }

        return discount;
    }

    // Convert Discount entity to DiscountDTO
    private DiscountDTO convertToDTO(Discount discount) {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setDiscountId(discount.getDiscountId());
        discountDTO.setDiscountCode(discount.getDiscountCode());
        discountDTO.setDescription(discount.getDescription());
        discountDTO.setDiscountType(discount.getDiscountType());
        discountDTO.setAmount(discount.getAmount());
        discountDTO.setValidFrom(discount.getValidFrom());
        discountDTO.setValidUntil(discount.getValidUntil());
        discountDTO.setStatus(discount.getStatus());
        discountDTO.setApplicableTo(discount.getApplicableTo());

        // Include the order IDs in the DTO
//        List<Long> orderIds = discount.getOrders().stream()
//                .map(Order::getId)
//                .collect(Collectors.toList());
//        discountDTO.setOrderIds(orderIds);

        return discountDTO;
    }
}
