package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "productId", target = "id")
    @Mapping(source = "productName", target = "name")
    @Mapping(source = "productDescription", target = "description")
    @Mapping(source = "productPrice", target = "price")
    @Mapping(source = "productStockQuantity", target = "stockQuantity")
    @Mapping(source = "productImage", target = "imageUrl")
    @Mapping(source = "category.categoryId", target = "categoryId")
    ProductDTO toProductDTO(Product product);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "name", target = "productName")
    @Mapping(source = "description", target = "productDescription")
    @Mapping(source = "price", target = "productPrice")
    @Mapping(source = "stockQuantity", target = "productStockQuantity")
    @Mapping(source = "imageUrl", target = "productImage")
    @Mapping(source = "categoryId", target = "category.categoryId")
    Product toProduct(ProductDTO productDTO);
}
