package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.CategoryDTO;
import com.example.ecommerce.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-10T03:01:53-0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO toCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getCategoryId() );
        categoryDTO.setName( category.getCategoryName() );

        return categoryDTO;
    }

    @Override
    public Category toCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setCategoryId( categoryDTO.getId() );
        category.setCategoryName( categoryDTO.getName() );

        return category;
    }
}
