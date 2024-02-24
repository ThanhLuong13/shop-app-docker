package com.project.shopapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {
    @JsonProperty("order_id")
    @Min(value=1, message = "Order's ID must be > 0")
    private int orderId;

    @Min(value=1, message = "Product's ID must be > 0")
    @JsonProperty("product_id")
    private int productId;

    @Min(value=0, message = "Product's ID must be >= 0")
    private float price;

    @Min(value=1, message = "number_of_products must be >= 1")
    @JsonProperty("number_of_products")
    private int numberOfProducts;

    @Min(value=0, message = "total_money must be >= 0")
    @JsonProperty("total_price")
    private float totalPrice;

    @NotBlank(message = "Choose your favorite color")
    private String color;
}
