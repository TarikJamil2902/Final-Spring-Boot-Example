package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.ProductDTO;
import com.tj.inventorySpringBoot.entity.Product;
import com.tj.inventorySpringBoot.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        // Convert DTO to Entity
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setCategory(productDTO.getCategory());
        product.setBrand(productDTO.getBrand());
        product.setImageUrl(productDTO.getImageUrl());
        product.setActive(productDTO.isActive());

        // Save the entity to the database
        Product savedProduct = productRepository.save(product);

        // Map the saved entity back to DTO
        ProductDTO savedProductDTO = new ProductDTO();
        savedProductDTO.setId(savedProduct.getId());
        savedProductDTO.setName(savedProduct.getName());
        savedProductDTO.setDescription(savedProduct.getDescription());
        savedProductDTO.setPrice(savedProduct.getPrice());
        savedProductDTO.setQuantity(savedProduct.getQuantity());
        savedProductDTO.setCategory(savedProduct.getCategory());
        savedProductDTO.setBrand(savedProduct.getBrand());
        savedProductDTO.setImageUrl(savedProduct.getImageUrl());
        savedProductDTO.setActive(savedProduct.isActive());

        return savedProductDTO;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        // Convert Entity to DTO
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setCategory(product.getCategory());
        productDTO.setBrand(product.getBrand());
        productDTO.setImageUrl(product.getImageUrl());
        productDTO.setActive(product.isActive());

        return productDTO;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setCategory(product.getCategory());
            productDTO.setBrand(product.getBrand());
            productDTO.setImageUrl(product.getImageUrl());
            productDTO.setActive(product.isActive());
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        // Update fields
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setCategory(productDTO.getCategory());
        product.setBrand(productDTO.getBrand());
        product.setImageUrl(productDTO.getImageUrl());
        product.setActive(productDTO.isActive());

        // Save updated entity
        Product updatedProduct = productRepository.save(product);

        // Convert updated entity to DTO
        ProductDTO updatedProductDTO = new ProductDTO();
        updatedProductDTO.setId(updatedProduct.getId());
        updatedProductDTO.setName(updatedProduct.getName());
        updatedProductDTO.setDescription(updatedProduct.getDescription());
        updatedProductDTO.setPrice(updatedProduct.getPrice());
        updatedProductDTO.setQuantity(updatedProduct.getQuantity());
        updatedProductDTO.setCategory(updatedProduct.getCategory());
        updatedProductDTO.setBrand(updatedProduct.getBrand());
        updatedProductDTO.setImageUrl(updatedProduct.getImageUrl());
        updatedProductDTO.setActive(updatedProduct.isActive());

        return updatedProductDTO;
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        productRepository.delete(product);
    }

//    public Page<ProductDTO> getAllProducts(int page, int size, String sortBy, String sortDir) {
//        Sort sort = Sort.by(Sort.Order.asc(sortBy)); // Default is ascending order
//        if ("desc".equalsIgnoreCase(sortDir)) {
//            sort = Sort.by(Sort.Order.desc(sortBy));
//        }
//
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        Page<ProductDTO> productDTOPage = productRepository.findAll(pageable).map(product -> mapToDTO(product, new ProductDTO()));
//        return productDTOPage;
//    }


//    public Page<ProductDTO> searchUsers(String searchTerm, int page, int size, String sortBy, String sortDir) {
//        Sort sort = Sort.by(Sort.Order.asc(sortBy)); // Default to ascending order
//        if ("desc".equalsIgnoreCase(sortDir)) {
//            sort = Sort.by(Sort.Order.desc(sortBy));
//        }
//        Pageable pageable = PageRequest.of(page, size, sort);
//        Page<Product> userPage = productRepository.findByUserNameContainingIgnoreCaseOrUserFirstNameContainingIgnoreCaseOrUserLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
//                searchTerm, searchTerm, searchTerm, searchTerm, pageable);
//
//        return userPage.map(user -> mapToDTO(user, new UserDTO()));
//    }

}
