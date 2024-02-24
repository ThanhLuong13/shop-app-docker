package com.project.shopapp.Responses;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.model.OrderEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse extends BaseResponse {

    private int id;

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("fullname")
    private String fullName;

    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

    private String note;

    @JsonProperty("order_date")
    private String orderDate;

    private String status;

    @JsonProperty("total_price")
    private float totalPrice;

    @JsonProperty("shipping_method")
    private String shippingMethod;

    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("shipping_date")
    private String shippingDate;

    @JsonProperty("payment_method")
    private String paymentMethod;

    @JsonProperty("order_details")
    private List<OrderDetailResponse> orderDetails;

    public static OrderResponse formOrderResponse(OrderEntity orderEntity) {
        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId(orderEntity.getId());
        orderResponse.setUserId(orderEntity.getUser().getId());
        orderResponse.setFullName(orderEntity.getFullName());
        orderResponse.setEmail(orderEntity.getEmail());
        orderResponse.setPhoneNumber(orderEntity.getPhoneNumber());
        orderResponse.setAddress(orderEntity.getAddress());
        orderResponse.setOrderDate(orderEntity.getOrderDate().toString());
        orderResponse.setNote(orderEntity.getNote());
        orderResponse.setStatus(orderEntity.getStatus());
        orderResponse.setTotalPrice(orderEntity.getTotalPrice());
        orderResponse.setShippingMethod(orderEntity.getShippingMethod());
        orderResponse.setShippingAddress(orderEntity.getShippingAddress());
        orderResponse.setShippingDate(orderEntity.getShippingDate().toString());
        orderResponse.setPaymentMethod(orderEntity.getPaymentMethod());
        if (orderEntity.getOrderDetails() == null) {
            orderResponse.setOrderDetails(new ArrayList<>());
        } else {
            List<OrderDetailResponse> orderDetailResponses = orderEntity.getOrderDetails().stream()
                    .map(OrderDetailResponse::fromOrderDetail)
                    .collect(Collectors.toList());
            orderResponse.setOrderDetails(orderDetailResponses);
        }


        return orderResponse;
    }
}
