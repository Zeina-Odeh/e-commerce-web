package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-10T02:39:32-0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getProductId() );
        productDTO.setName( product.getProductName() );
        productDTO.setDescription( product.getProductDescription() );
        productDTO.setPrice( product.getProductPrice() );
        productDTO.setStockQuantity( product.getProductStockQuantity() );
        productDTO.setImageUrl( product.getProductImage() );
        productDTO.setCategoryId( productCategoryCategoryId( product ) );

        return productDTO;
    }

    @Override
    public Product toProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( productDTOToCategory( productDTO ) );
        product.setProductId( productDTO.getId() );
        product.setProductName( productDTO.getName() );
        product.setProductDescription( productDTO.getDescription() );
        product.setProductPrice( productDTO.getPrice() );
        product.setProductStockQuantity( productDTO.getStockQuantity() );
        product.setProductImage( productDTO.getImageUrl() );

        return product;
    }

    private Long productCategoryCategoryId(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        Long categoryId = category.getCategoryId();
        if ( categoryId == null ) {
            return null;
        }
        return categoryId;
    }

    protected Category productDTOToCategory(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setCategoryId( productDTO.getCategoryId() );

        return category;
    }
}
