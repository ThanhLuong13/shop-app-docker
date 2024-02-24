package com.project.shopapp.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private int id;

    @JsonProperty("user_id")
    @NotNull
    private int userId;

    @JsonProperty("fullname")
    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    private String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;

    private String note;

    private String status;

    @JsonProperty("total_price")
    @NotNull(message = "Total price is not null")
    private float totalPrice;

    @JsonProperty("shipping_method")
    @NotBlank(message = "Shipping method is required")
    private String shippingMethod;

    @JsonProperty("shipping_address")
    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;

    @JsonProperty("shipping_date")
    private LocalDate shippingDate;

    @JsonProperty("payment_method")
    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    @JsonProperty("cart_items")
    private List<CartItemsDTO> cartItems;
}
