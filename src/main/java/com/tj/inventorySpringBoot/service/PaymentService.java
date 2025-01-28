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

    // Method to create a new payment
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = convertToEntity(paymentDTO);
        Payment savedPayment = paymentRepository.save(payment);
        return convertToDTO(savedPayment);
    }

    // Method to update an existing payment
    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            payment.setAmount(paymentDTO.getAmount());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentStatus(paymentDTO.getPaymentStatus());

            // Save updated payment
            Payment updatedPayment = paymentRepository.save(payment);
            return convertToDTO(updatedPayment);
        }
        return null; // Return null if payment is not found (you can handle it in the controller)
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
        return null; // Return null or handle with an exception
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
        return payment;
    }

    // Convert Payment entity to PaymentDTO
    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaymentMethod(payment.getPaymentMethod());
        paymentDTO.setPaymentStatus(payment.getPaymentStatus());

        // Optionally set orderId if needed
        if (payment.getOrder() != null) {
            paymentDTO.setOrderId(payment.getOrder().getId());
        }

        return paymentDTO;
    }
}
