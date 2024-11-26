package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.CartItemDTO;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-14T15:23:47+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class CartItemMapperImpl implements CartItemMapper {

    @Override
    public CartItemDTO cartItemToCartItemDTO(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemDTO cartItemDTO = new CartItemDTO();

        cartItemDTO.setProductId( cartItemProductProductId( cartItem ) );
        cartItemDTO.setUserId( cartItemUserUserId( cartItem ) );
        cartItemDTO.setQuantity( cartItem.getQuantity() );

        return cartItemDTO;
    }

    @Override
    public CartItem cartItemDTOToCartItem(CartItemDTO cartItemDTO) {
        if ( cartItemDTO == null ) {
            return null;
        }

        CartItem cartItem = new CartItem();

        cartItem.setProduct( cartItemDTOToProduct( cartItemDTO ) );
        cartItem.setUser( cartItemDTOToUser( cartItemDTO ) );
        cartItem.setQuantity( cartItemDTO.getQuantity() );

        return cartItem;
    }

    private Long cartItemProductProductId(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }
        Product product = cartItem.getProduct();
        if ( product == null ) {
            return null;
        }
        Long productId = product.getProductId();
        if ( productId == null ) {
            return null;
        }
        return productId;
    }

    private Long cartItemUserUserId(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }
        User user = cartItem.getUser();
        if ( user == null ) {
            return null;
        }
        Long userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    protected Product cartItemDTOToProduct(CartItemDTO cartItemDTO) {
        if ( cartItemDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductId( cartItemDTO.getProductId() );

        return product;
    }

    protected User cartItemDTOToUser(CartItemDTO cartItemDTO) {
        if ( cartItemDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( cartItemDTO.getUserId() );

        return user;
    }
}
