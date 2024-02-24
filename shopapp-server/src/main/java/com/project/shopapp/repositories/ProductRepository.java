package com.project.shopapp.repositories;

import com.project.shopapp.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    boolean existsByName(String name);

    @Query("SELECT p FROM ProductEntity p " +
            "WHERE (:categoryId IS NULL OR :categoryId = 0 OR p.category.id = :categoryId) " +
            "AND (:keyword IS NULL OR :keyword = '' OR p.name LIKE %:keyword% OR p.description LIKE %:keyword%)")
    Page<ProductEntity> searchProducts(@Param("keyword") String keyword,
                                       @Param("categoryId") Integer categoryId,
                                       Pageable pageable);//phân trang

    @Query("SELECT p FROM ProductEntity p LEFT JOIN FETCH p.productImages WHERE p.id = :id")
    Optional<ProductEntity> findProductById(@Param("id") Integer id);

    @Query("SELECT p FROM ProductEntity p WHERE p.id IN :ids")
    List<ProductEntity> findProductsByIds(@Param("ids") List<Integer> ids);

    @Modifying
    @Query("UPDATE ProductEntity p SET p.thumbnail = :imageUrl WHERE p.id = :productId")
    void updateProductThumbnail(@Param("productId") Integer productId,@Param("imageUrl") String imageUrl);
}
