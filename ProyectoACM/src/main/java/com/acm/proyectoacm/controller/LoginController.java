package com.acm.proyectoacm.controller;

import com.acm.proyectoacm.dto.CartDTO;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.acm.proyectoacm.dto.UserDTO;
import com.acm.proyectoacm.dto.UserFormDTO;
import com.acm.proyectoacm.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
public class LoginController {

    private final IUserService userService;

    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("userForm", new UserFormDTO());
        return "login"; // login.html
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("userForm") @Valid UserFormDTO form,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            return "login";
        }

        List<UserDTO> users = userService.getAllUsers();
        UserDTO matchedUser = users.stream()
                .filter(u -> u.getUsuario().equals(form.getUsuario()) && u.getClave().equals(form.getClave()))
                .findFirst()
                .orElse(null);

        if (matchedUser == null) {
            model.addAttribute("error", "Credenciales inválidas");
            return "login";
        }

        model.addAttribute("user", matchedUser);
        model.addAttribute("products", userService.getAllProducts());

        return "login";
    }

    @GetMapping("/carts/{userId}")
    public String showCartsByUser(@PathVariable int userId, Model model) {
        List<CartDTO> carts = userService.getCartsByUserId(userId);
        model.addAttribute("carts", carts);
        return "cart"; // cart es el html
    }
    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/login";
    }
}

