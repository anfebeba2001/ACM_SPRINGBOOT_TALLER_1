package com.acm.proyectoacm.dto;

import lombok.Data;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {
   // @JsonProperty("userId")
    private int id;
    private int userId;
    private String date;
    private List<CartProductDTO> products;
}