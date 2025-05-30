package com.acm.proyectoacm.services;

import com.acm.proyectoacm.dto.CartDTO;
import com.acm.proyectoacm.dto.ProductDTO;
import com.acm.proyectoacm.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsers();


    UserDTO getUserByid(int id);

    List<CartDTO> getCartsByUserId(int userId);
    List<ProductDTO> getAllProducts();
    String login(String username, String password);

}
