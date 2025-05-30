package com.acm.proyectoacm.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserFormDTO {
    @NotBlank
    private String usuario;

    @NotBlank
    private String clave;
}
