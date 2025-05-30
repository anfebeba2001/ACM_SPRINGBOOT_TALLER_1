package com.acm.proyectoacm.controller;

import com.acm.proyectoacm.dto.ProductDTO;
import com.acm.proyectoacm.dto.UserDTO;
import com.acm.proyectoacm.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UserDTO user = userService.getUserByid(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(404).body("Usuario no encontrado");
    }

    @GetMapping("/{id}/carts")
    public ResponseEntity<?> getCartsByUserId(@PathVariable int id) {
        List<?> carts = userService.getCartsByUserId(id);
        return carts.isEmpty() ? ResponseEntity.status(404).body("No se encontraron carritos para el usuario") : ResponseEntity.ok(carts);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("Faltan datos: username y password son obligatorios");
        }

        String token= userService.login(username, password);
        if (token == null) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
        
        return token != null ? ResponseEntity.ok(Map.of("token", token)) : ResponseEntity.status(401).body("Credenciales inválidas");
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = userService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}