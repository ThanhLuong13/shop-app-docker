package com.project.shopapp.services;
import com.project.shopapp.Responses.ProductResponse;
import com.project.shopapp.dto.ProductDTO;
import com.project.shopapp.dto.ProductImageDTO;
import com.project.shopapp.model.ProductEntity;
import com.project.shopapp.model.ProductImageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
    ProductEntity createProduct(ProductDTO productDTO) throws Exception;
    ProductEntity getProductById(int id) throws Exception;
    Page<ProductResponse> searchProducts(String keyword, int categoryId, PageRequest pageRequest);
    ProductEntity updateProduct(int id, ProductDTO productDTO) throws Exception;
    void deleteProduct(int id);
    boolean existsByName(String name);
    List<ProductImageEntity> createProductImage(
            int productId, List<MultipartFile> files) throws Exception;

    List<ProductEntity> getProductsByIds(List<Integer> ids);
}
