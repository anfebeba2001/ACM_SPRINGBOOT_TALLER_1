package com.ACM.PrimerTaller.backend.DTO.Auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDTO {
    @NotBlank(message = "El nombre de usuario no puede estar vacío.")
    private String username;
    @NotBlank(message = "La contraseña no puede estar vacía.")
    private String password;
}
