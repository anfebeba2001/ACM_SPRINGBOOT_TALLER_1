package com.ACM.PrimerTaller.backend.Services;

import com.ACM.PrimerTaller.backend.DTO.Auth.AuthRequestDTO;
import com.ACM.PrimerTaller.backend.DTO.Auth.AuthResponseDTO;
import com.ACM.PrimerTaller.backend.DTO.Product.Cart;
import com.ACM.PrimerTaller.backend.DTO.Product.Product;

import java.util.List;
import java.util.Optional;

public interface FakeStoreApiService {
    Optional<AuthResponseDTO> authenticate(AuthRequestDTO authRequestDTO);
    List<Product> getAllProducts();
    List<Cart> getCartsByUserId(Integer userId);
}
