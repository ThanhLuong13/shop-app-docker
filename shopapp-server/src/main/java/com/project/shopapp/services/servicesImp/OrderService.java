package com.project.shopapp.services.servicesImp;


import com.project.shopapp.dto.CartItemsDTO;
import com.project.shopapp.dto.OrderDTO;
import com.project.shopapp.dto.OrderDetailDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.model.OrderDetailEntity;
import com.project.shopapp.model.OrderEntity;
import com.project.shopapp.model.UserEntity;
import com.project.shopapp.repositories.OrderDetailRepository;
import com.project.shopapp.repositories.OrderRepository;
import com.project.shopapp.repositories.UserRepository;
import com.project.shopapp.services.IOrderService;
import com.project.shopapp.utilities.SD.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailService orderDetailService;

    @Override
    public OrderEntity createOrder(OrderDTO orderDTO) throws Exception {
        UserEntity user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id "+ orderDTO.getUserId()));
        OrderEntity order = mapToEntity(orderDTO);
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING.toString());
        LocalDateTime now = LocalDateTime.now();
        order.setOrderDate(now);
        LocalDate shippingDate = orderDTO.getShippingDate() == null
                ? now.toLocalDate().plusDays(1)
                : orderDTO.getShippingDate();
        if (shippingDate.isBefore(now.toLocalDate())){
            throw new Exception("Shipping date must after Order date");
        }
        order.setShippingDate(shippingDate);
        order.setActive(true);
        orderRepository.save(order);
        // Create Order Detail
        for (CartItemsDTO cartItemsDTO : orderDTO.getCartItems()) {
            OrderDetailDTO orderDetail = new OrderDetailDTO();
            orderDetail.setOrderId(order.getId());
            orderDetail.setProductId(cartItemsDTO.getProductId());
            orderDetail.setNumberOfProducts(cartItemsDTO.getQuantity());
            OrderDetailEntity orderDetailEntity = orderDetailService.createOrderDetail(orderDetail);
        }
        return order;
    }

    @Override
    public OrderEntity getOrder(int id) throws DataNotFoundException {
        return (orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can not find order with id " + id)));
    }

    @Override
    @Transactional
    public OrderEntity updateOrder(int id, OrderDTO orderDTO) throws Exception {
        OrderEntity existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can not find order with id " + id));
        userRepository.findById(orderDTO.getUserId()).
                orElseThrow(() -> new DataNotFoundException("Can not find user with id " + orderDTO.getUserId()));
        if (existingOrder.getStatus().equals(OrderStatus.DELIVERED.toString())) {
            throw new Exception("Your oder is shipping");
        }
        OrderEntity updateOrder = mapToEntity(orderDTO);
        updateOrder.setId(id);
        LocalDate shippingDate = orderDTO.getShippingDate() == null
                ? existingOrder.getShippingDate() : orderDTO.getShippingDate();
        if (shippingDate.isBefore(existingOrder.getOrderDate().toLocalDate())){
            throw new Exception("Shipping date must after Order date");
        }
        updateOrder.setShippingDate(shippingDate);
        orderRepository.save(updateOrder);
        return updateOrder;
    }

    @Override
    @Transactional
    public void deleteOrder(int id) {
        OrderEntity order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setActive(false);
            orderRepository.save(order);
        }

    }

    @Override
    public List<OrderEntity> findByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Page<OrderEntity> searchOrders(String keyword, Pageable pageable) {
        return orderRepository.searchOrders(keyword, pageable);
    }

    @Override
    @Transactional
    public OrderEntity updateOrderStatus(int id, String status) throws Exception{
        OrderEntity existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can not find order with id " + id));
        if (status.equals(OrderStatus.SHIPPED.toString()) && existingOrder.getStatus().equals(OrderStatus.DELIVERED.toString())) {
            throw new Exception("Your order is shipping");
        }
        existingOrder.setStatus(status);
        orderRepository.save(existingOrder);
        return existingOrder;

    }
    private OrderEntity mapToEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, OrderEntity.class);
    }

}
