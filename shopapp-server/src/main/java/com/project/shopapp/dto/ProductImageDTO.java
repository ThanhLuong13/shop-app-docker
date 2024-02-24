package com.project.shopapp.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDTO {

    private int id;

    @JsonProperty("product_id")
    private int productId;

    @JsonProperty("image_url")
    private String imageUrl;

    public static final int maxProductFile = 5;
}
