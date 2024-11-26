package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.CartItemDTO;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.exception.UnauthorizedAccessException;
import com.example.ecommerce.mapper.CartItemMapper;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class CartItemService {

    private final  CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemMapper cartItemMapper;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, UserRepository userRepository,
                           ProductRepository productRepository, CartItemMapper cartItemMapper) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartItemMapper = cartItemMapper;
    }

    public List<CartItemDTO> getCartItemsForUser(Long userId) {
        return cartItemRepository.findByUser_userId(userId)
                .stream()
                .map(cartItemMapper::cartItemToCartItemDTO)
                .collect(Collectors.toList());
    }

    public CartItemDTO addToCart(CartItemDTO cartItemDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = userRepository.findByUserEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        CartItem cartItem = cartItemMapper.cartItemDTOToCartItem(cartItemDTO);

        Product product = productRepository.findById(cartItemDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        User user = userRepository.findById(cartItemDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!authenticatedUser.getUserId().equals(user.getUserId())) {
            throw new UnauthorizedAccessException("User mismatch");
        }

        cartItem.setProduct(product);
        cartItem.setUser(user);
        cartItem.setQuantity(cartItemDTO.getQuantity());

        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.cartItemToCartItemDTO(savedCartItem);
    }

    public void removeCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
