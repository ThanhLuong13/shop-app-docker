package com.project.shopapp.repositories;

import com.project.shopapp.model.OrderEntity;

import org.hibernate.query.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    //Tìm các đơn hàng của 1 user nào đó

    @Query("select o from OrderEntity o where o.active = true ")
    List<OrderEntity> findByUserId(Integer userId);

    @Query("select o from OrderEntity o left join fetch o.orderDetails where o.id = :id")
    Optional<OrderEntity> findById(@Param("id") int id);

    @Query("SELECT o " +
            "FROM OrderEntity o " +
            "WHERE o.active = true AND (:keyword IS NULL OR :keyword = '' " +
            "OR o.fullName like %:keyword% " +
            "OR o.phoneNumber like %:keyword%)")
    Page<OrderEntity> searchOrders(@Param("keyword") String keyword,
                                   Pageable pageable);

    @Modifying
    @Query("UPDATE OrderEntity o SET o.status = :status WHERE o.id = :orderId")
    OrderEntity updateOrderStatus(@Param("orderId") int orderId, @Param("status") String Status);
}
