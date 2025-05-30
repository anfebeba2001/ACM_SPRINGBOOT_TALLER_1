package com.acm.proyectoacm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;

}