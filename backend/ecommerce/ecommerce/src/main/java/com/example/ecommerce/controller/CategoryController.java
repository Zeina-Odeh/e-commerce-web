package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CategoryDTO;
import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.ImageEntity;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ImageRepository;
import com.example.ecommerce.service.impl.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService, ImageRepository imageRepository, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<CategoryDTO> getCategories() {
        return categoryService.getAllCategories();
    }

//    @PreAuthorize("hasRole('admin')")
    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            System.out.println("User Authorities: " + authentication.getAuthorities());
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            System.out.println("User Roles: " + authorities);
        }


        CategoryDTO savedCategory = categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

//    @PreAuthorize("hasRole('admin')")
//    @PostMapping
//    public ResponseEntity<?> addCategory(
//            @RequestParam("category") String categoryJson,
//            @RequestParam("image") MultipartFile imageFile) {
//
//        try {
//            // Parse category JSON
//            CategoryDTO categoryDTO = new ObjectMapper().readValue(categoryJson, CategoryDTO.class);
//
//            Category category = new Category();
//            category.setCategoryName(categoryDTO.getName());
//
//            // Save image and get the saved image entity
//            ImageEntity imageEntity = saveImage(imageFile);
//
//            // Set the image to the category if needed
//            category.setImage(imageEntity);
//
//            // Save category entity
//            categoryRepository.save(category);
//
//            return ResponseEntity.ok(category); // Return the saved category or any relevant object
//
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the image file.");
//        }
//    }
//
//    private ImageEntity saveImage(MultipartFile image) {
//        try {
//            byte[] imageBytes = image.getBytes();
//            ImageEntity imageEntity = new ImageEntity(imageBytes);
//            return imageRepository.save(imageEntity);
//        } catch (IOException e) {
//            throw new RuntimeException("Error saving image", e);
//        }
//    }

}
