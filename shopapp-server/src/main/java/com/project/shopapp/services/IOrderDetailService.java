package com.project.shopapp.services;

import com.project.shopapp.Responses.OrderDetailResponse;
import com.project.shopapp.dto.OrderDetailDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.model.OrderDetailEntity;

import java.util.List;
public interface IOrderDetailService {
    OrderDetailEntity createOrderDetail(OrderDetailDTO newOrderDetail) throws DataNotFoundException;
    OrderDetailEntity getOrderDetail(int id) throws DataNotFoundException;
    OrderDetailEntity updateOrderDetail(int id, OrderDetailDTO newOrderDetailData)
            throws DataNotFoundException;
    void deleteById(int id);
    List<OrderDetailEntity> findByOrderId(int orderId);
}

