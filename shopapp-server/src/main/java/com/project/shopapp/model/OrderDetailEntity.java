package com.project.shopapp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private ProductEntity product;

    @Column(name = "price")
    private float price;

    @Column(name = "number_of_products")
    private int numberOfProducts;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "color")
    private String color;
}
