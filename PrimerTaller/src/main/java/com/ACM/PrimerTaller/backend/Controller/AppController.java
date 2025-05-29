package com.ACM.PrimerTaller.backend.Controller;

import com.ACM.PrimerTaller.backend.DTO.Auth.AuthRequestDTO;
import com.ACM.PrimerTaller.backend.DTO.Auth.AuthResponseDTO;
import com.ACM.PrimerTaller.backend.DTO.Product.Cart;
import com.ACM.PrimerTaller.backend.DTO.Product.Product;
import com.ACM.PrimerTaller.backend.Services.FakeStoreApiService;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
public class AppController {
    private final FakeStoreApiService fakeStoreApiService;

    public AppController(FakeStoreApiService fakeStoreApiService) {
        this.fakeStoreApiService = fakeStoreApiService;
    }

    @ModelAttribute("authRequest")
    public AuthRequestDTO authRequest() {
        return new AuthRequestDTO();
    }

    @GetMapping("/")
    public String showLoginForm(Model model){
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@Valid @ModelAttribute("authRequest") AuthRequestDTO authRequestDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errorMessage","Por favor ingresa tu usuario y contraseña.");
            return "redirect:/";
        }

        Optional<AuthResponseDTO> authResponseDTO = fakeStoreApiService.authenticate(authRequestDTO);
        if (authResponseDTO.isPresent()){
            return "redirect:/products";
        }
        else {
            redirectAttributes.addFlashAttribute("errorMessage","Nombre de usuario o contraseña incorrectos.");
            return "redirect:/";
        }
    }

    @GetMapping("/products")
    public String showProducts(Model model){
        List<Product> products = fakeStoreApiService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/searchCarts")
    public String searchCartsByUserId(@RequestParam("userId") @NotNull(message = "El ID de usuario no puede estar vacío.") Integer userId,
                                      RedirectAttributes redirectAttributes,
                                      Model model){

        if(userId == null){
            redirectAttributes.addFlashAttribute("errorMessage","El ID de usuario no puede estar vacío.");
            return "redirect:/products";
        }

        List<Cart> carts = fakeStoreApiService.getCartsByUserId(userId);
        model.addAttribute("carts", carts);
        model.addAttribute("searchUserId",userId);
        model.addAttribute("products", fakeStoreApiService.getAllProducts());

        return "products";
    }
}
