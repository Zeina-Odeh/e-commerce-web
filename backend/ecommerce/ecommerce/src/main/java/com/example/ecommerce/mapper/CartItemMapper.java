package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.CartItemDTO;
import com.example.ecommerce.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    @Mapping(source = "product.productId", target = "productId")
    @Mapping(source = "user.userId", target = "userId")
    CartItemDTO cartItemToCartItemDTO(CartItem cartItem);

    @Mapping(source = "productId ", target = "product.productId")
    @Mapping(source = "userId", target = "user.userId")
    CartItem cartItemDTOToCartItem(CartItemDTO cartItemDTO);
}
