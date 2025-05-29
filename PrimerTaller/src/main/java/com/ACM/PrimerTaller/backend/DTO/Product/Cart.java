package com.ACM.PrimerTaller.backend.DTO.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Integer id;
    private Integer userId;
    private OffsetDateTime date;
    private List<CartProduct> products;
}
