package com.project.shopapp.services.servicesImp;

import com.project.shopapp.Responses.OrderDetailResponse;
import com.project.shopapp.Responses.OrderResponse;
import com.project.shopapp.dto.OrderDTO;
import com.project.shopapp.dto.OrderDetailDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.model.OrderDetailEntity;
import com.project.shopapp.model.OrderEntity;
import com.project.shopapp.model.ProductEntity;
import com.project.shopapp.repositories.OrderDetailRepository;
import com.project.shopapp.repositories.OrderRepository;
import com.project.shopapp.repositories.ProductRepository;
import com.project.shopapp.services.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService {

    private final ModelMapper modelMapper;

    private final OrderDetailRepository orderDetailRepository;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    @Override
    public OrderDetailEntity createOrderDetail(OrderDetailDTO orderDetailDTO) throws DataNotFoundException {
        OrderEntity order = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() ->
                        new DataNotFoundException("Can not find Order with id "+ orderDetailDTO.getOrderId()));
        ProductEntity product = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() ->
                        new DataNotFoundException("Can not find Product with id "+ orderDetailDTO.getProductId()));
        OrderDetailEntity newOrderDetail = new OrderDetailEntity();
        newOrderDetail.setOrder(order);
        newOrderDetail.setProduct(product);
        newOrderDetail.setPrice(product.getPrice());
        newOrderDetail.setColor(orderDetailDTO.getColor());
        newOrderDetail.setNumberOfProducts(orderDetailDTO.getNumberOfProducts());
        float totalPrice = newOrderDetail.getPrice() * newOrderDetail.getNumberOfProducts();
        newOrderDetail.setTotalPrice(totalPrice);
        orderDetailRepository.save(newOrderDetail);
        return newOrderDetail;
    }

    @Override
    public OrderDetailEntity getOrderDetail(int id) throws DataNotFoundException {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can not find Order Detail with id "+ id));
    }

    @Override
    @Transactional
    public OrderDetailEntity updateOrderDetail(int id,
                                               OrderDetailDTO newOrderDetailDTO)
            throws DataNotFoundException
    {
        OrderDetailEntity existingOrderDetail = getOrderDetail(id);
        OrderEntity order = orderRepository.findById(newOrderDetailDTO.getOrderId())
                .orElseThrow(() ->
                        new DataNotFoundException("Can not find Order with id "+ newOrderDetailDTO.getOrderId()));
        ProductEntity product = productRepository.findById(newOrderDetailDTO.getProductId())
                .orElseThrow(()
                        -> new DataNotFoundException("Can not find Product with id "+ newOrderDetailDTO.getProductId()));
        existingOrderDetail.setPrice(newOrderDetailDTO.getPrice());
        existingOrderDetail.setProduct(product);
        existingOrderDetail.setOrder(order);
        existingOrderDetail.setColor(newOrderDetailDTO.getColor());
        existingOrderDetail.setTotalPrice(newOrderDetailDTO.getTotalPrice());
        existingOrderDetail.setNumberOfProducts(newOrderDetailDTO.getNumberOfProducts());
        orderDetailRepository.save(existingOrderDetail);
        return existingOrderDetail;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetailEntity> findByOrderId(int orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    private OrderDetailResponse maptoResponse(OrderDetailEntity orderDetailEntity) {
        return modelMapper.map(orderDetailEntity, OrderDetailResponse.class);
    }

    private OrderDetailEntity maptoEntity(OrderDetailDTO orderDetailDTO) {
        return modelMapper.map(orderDetailDTO, OrderDetailEntity.class);
    }
}
