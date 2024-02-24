package com.project.shopapp.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.model.BaseEntity;
import com.project.shopapp.model.CategoryEntity;
import com.project.shopapp.model.ProductEntity;
import com.project.shopapp.model.ProductImageEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse extends BaseResponse {

    private int id;

    private String name;

    private float price;

    private String thumbnail;

    private String description;

    private CategoryEntity category;

    @JsonProperty("product_images")
    private List<ProductImageResponse> productImages;

    public static ProductResponse formResponse (ProductEntity product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setUpdatedAt(product.getUpdatedAt());
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setThumbnail(product.getThumbnail());
        productResponse.setCategory(product.getCategory());
        if (product.getProductImages() == null) {
            productResponse.setProductImages(new ArrayList<>());
        } else {
            List<ProductImageResponse> productImageResponseList = new ArrayList<>();
            for (ProductImageEntity productImageEntity : product.getProductImages()) {
                ProductImageResponse productImage = new ProductImageResponse();
                productImage.setProductId(product.getId());
                productImage.setImageUrl(productImageEntity.getImageUrl());
                productImageResponseList.add(productImage);
                productResponse.setProductImages(productImageResponseList);
            }
        }
        return productResponse;
    }

}
