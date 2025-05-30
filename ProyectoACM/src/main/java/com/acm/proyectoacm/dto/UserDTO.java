package com.acm.proyectoacm.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    @JsonProperty("id")
    private int id;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @JsonProperty("username")
    private String usuario;

    @NotBlank(message = "La contraseña es obligatoria")
    @JsonProperty("password")
    private String clave;

    private String email;
}