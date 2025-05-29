package com.ACM.PrimerTaller.backend.DTO.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    private double rating;
    private int count;
}
