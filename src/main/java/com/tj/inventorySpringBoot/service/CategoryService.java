package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.CategoryDTO;
import com.tj.inventorySpringBoot.entity.Category;
import com.tj.inventorySpringBoot.entity.Product;
import com.tj.inventorySpringBoot.repository.CategoryRepository;
import com.tj.inventorySpringBoot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository; // To handle the relationship with Product

    // Method to create or update a category
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    // Method to get all categories
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Method to get a category by its ID
    public CategoryDTO getCategoryById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            return convertToDTO(categoryOptional.get());
        }
        return null; // You can throw an exception here or return a 404 response in the controller
    }

    // Method to delete a category by its ID
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    // Convert CategoryDTO to Category entity
    private Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        // Convert product IDs to Product entities
        if (categoryDTO.getProductIds() != null) {
            List<Product> products = productRepository.findAllById(categoryDTO.getProductIds());
            category.setProducts(products);
        }

        return category;
    }

    // Convert Category entity to CategoryDTO
    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());

        // Get product IDs from the associated products
        List<Long> productIds = category.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList());
        categoryDTO.setProductIds(productIds);

        return categoryDTO;
    }
}

