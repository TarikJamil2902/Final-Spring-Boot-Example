package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.PaymentDTO;
import com.tj.inventorySpringBoot.entity.Payment;
import com.tj.inventorySpringBoot.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Save or update a payment
    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        Payment payment = convertToEntity(paymentDTO);
        Payment savedPayment = paymentRepository.save(payment);
        return convertToDTO(savedPayment);
    }

    // Get all payments
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a payment by its ID
    public PaymentDTO getPaymentById(Long id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isPresent()) {
            return convertToDTO(paymentOptional.get());
        }
        return null; // You can throw an exception or return 404 in the controller
    }

    // Delete a payment by its ID
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    // Convert PaymentDTO to Payment entity
    private Payment convertToEntity(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setId(paymentDTO.getId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setPaymentStatus(paymentDTO.getPaymentStatus());

        // You can retrieve the associated Order entity if needed
        // Example: payment.setOrder(orderRepository.findById(paymentDTO.getOrderId()).orElse(null));

        return payment;
    }

    // Convert Payment entity to PaymentDTO
    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaymentMethod(payment.getPaymentMethod());
        paymentDTO.setPaymentStatus(payment.getPaymentStatus());

        // Set the orderId from the associated Order entity
        if (payment.getOrder() != null) {
            paymentDTO.setOrderId(payment.getOrder().getId());
        }

        return paymentDTO;
    }
}

