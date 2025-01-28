package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.ProductDTO;
import com.tj.inventorySpringBoot.entity.Category;
import com.tj.inventorySpringBoot.entity.Product;
import com.tj.inventorySpringBoot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Method to create or update a product
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    // Method to get all products
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return convertToDTOList(products);
    }

    // Method to get a product by its ID
    public ProductDTO getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return convertToDTO(productOptional.get());
        }
        return null; // You can handle this with exception or return a 404 in controller
    }

    // Method to delete a product by its ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Method to convert ProductDTO to Product entity
    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSize(productDTO.getSize());
        product.setColor(productDTO.getColor());
        product.setBrand(productDTO.getBrand());
        product.setPrice(productDTO.getPrice());
        // Assuming Category is set in DTO, you may need to fetch Category by its ID
        if (productDTO.getCategoryId() != null) {
            Category category = new Category();
            category.setId(productDTO.getCategoryId());
            product.setCategory(category);
        }
        return product;
    }

    // Method to convert Product entity to ProductDTO
    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setSize(product.getSize());
        productDTO.setColor(product.getColor());
        productDTO.setBrand(product.getBrand());
        productDTO.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            productDTO.setCategoryId(product.getCategory().getId());
        }
        return productDTO;
    }

    // Method to convert a list of Product entities to a list of ProductDTOs
    private List<ProductDTO> convertToDTOList(List<Product> products) {
        return products.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
