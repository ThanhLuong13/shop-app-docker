package com.project.shopapp.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.model.OrderDetailEntity;
import jakarta.validation.constraints.Min;
import lombok.*;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {

    private int id;
    @JsonProperty("order_id")
    private int orderId;

    @JsonProperty("product")
    private ProductResponse product;

    private float price;

    @JsonProperty("number_of_products")
    private int numberOfProducts;

    @JsonProperty("total_price")
    private float totalPrice;

    private String color;

    public static OrderDetailResponse fromOrderDetail(OrderDetailEntity orderDetail) {
        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
        orderDetailResponse.setId(orderDetail.getId());
        orderDetailResponse.setOrderId(orderDetail.getOrder().getId());
        orderDetailResponse.setProduct(ProductResponse.formResponse(orderDetail.getProduct()));
        orderDetailResponse.setPrice(orderDetail.getPrice());
        orderDetailResponse.setNumberOfProducts(orderDetail.getNumberOfProducts());
        orderDetailResponse.setTotalPrice(orderDetail.getTotalPrice());
        orderDetailResponse.setColor(orderDetail.getColor());
        return orderDetailResponse;
    }
}
