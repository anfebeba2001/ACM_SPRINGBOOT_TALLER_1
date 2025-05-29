package com.ACM.PrimerTaller.backend.DTO.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;
}
