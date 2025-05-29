package com.ACM.PrimerTaller.backend.DTO.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CartProduct {
    private Integer productId;
    private Integer quantity;
}
