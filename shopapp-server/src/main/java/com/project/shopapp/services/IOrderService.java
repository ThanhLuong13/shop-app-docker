package com.project.shopapp.services;

import com.project.shopapp.Responses.OrderResponse;
import com.project.shopapp.dto.OrderDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.model.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {

    OrderEntity createOrder(OrderDTO orderDTO) throws Exception;
    OrderEntity getOrder(int id) throws DataNotFoundException ;
    OrderEntity updateOrder(int id, OrderDTO orderDTO) throws Exception;
    void deleteOrder(int id);
    List<OrderEntity> findByUserId(int userId);
    Page<OrderEntity> searchOrders(String keyword, Pageable pageable);

    OrderEntity updateOrderStatus(int id, String status) throws Exception;
}
