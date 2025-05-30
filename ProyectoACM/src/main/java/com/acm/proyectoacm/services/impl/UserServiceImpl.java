package com.acm.proyectoacm.services.impl;

import com.acm.proyectoacm.dto.CartDTO;
import com.acm.proyectoacm.dto.ProductDTO;
import com.acm.proyectoacm.dto.UserDTO;
import com.acm.proyectoacm.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final RestTemplate restTemplate;

    @Override
    public List<UserDTO> getAllUsers() {
        UserDTO[] users = restTemplate.getForObject("/users", UserDTO[].class);
        return users != null ? Arrays.asList(users) : List.of(); // Manejo seguro de null
    }

    @Override
    public UserDTO getUserByid(int id) {
        return restTemplate.getForObject("/users/{id}", UserDTO.class, id);
    }

    @Override
    public List<CartDTO> getCartsByUserId(int userId) {
        CartDTO[] carts = restTemplate.getForObject("/carts/user/{id}", CartDTO[].class, userId);
        return carts != null ? Arrays.asList(carts) : List.of();
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        ProductDTO[] products = restTemplate.getForObject("/products", ProductDTO[].class);
        return products != null ? Arrays.asList(products) : List.of();
    }

    //Método para autenticar un usuario con el fakeapi

    public String login(String username, String password) {
        String url = "/auth/login";

        //Objeto con credenciales
        Map<String, String> credentials = Map.of(
            "username", username,
            "password", password
        );

        return restTemplate.postForObject(url, credentials, String.class);
    }
}