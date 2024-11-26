package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.CategoryDTO;
import com.example.ecommerce.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "categoryId", target = "id")
    @Mapping(source = "categoryName", target = "name")
//    @Mapping(source = "image", target = "image")
    CategoryDTO toCategoryDTO(Category category);

    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "name", target = "categoryName")
//    @Mapping(source = "image", target = "image")
    Category toCategory(CategoryDTO categoryDTO);
}
