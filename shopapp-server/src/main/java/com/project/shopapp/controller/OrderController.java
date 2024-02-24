package com.project.shopapp.controller;


import com.project.shopapp.Responses.OrderResponse;
import com.project.shopapp.Responses.OrdersListResponse;
import com.project.shopapp.components.LocalizationUtils;
import com.project.shopapp.dto.OrderDTO;
import com.project.shopapp.dto.UpdateOrderStatusDTO;
import com.project.shopapp.model.OrderEntity;
import com.project.shopapp.repositories.OrderRepository;
import com.project.shopapp.services.servicesImp.OrderService;
import com.project.shopapp.utilities.MessageKeys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final LocalizationUtils localizationUtils;

    @PostMapping("")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO orderDTO,
                                         BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            OrderEntity newOrder = orderService.createOrder(orderDTO);
            return ResponseEntity.ok(OrderResponse.formOrderResponse(newOrder));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //GET http://localhost:8088/api/v1/orders/2
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@Valid @PathVariable("id") int orderId) {
        try {
            OrderEntity order = orderService.getOrder(orderId);
            return ResponseEntity.ok(OrderResponse.formOrderResponse(order));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{user_id}") // Thêm biến đường dẫn "user_id"
    //GET http://localhost:8088/api/v1/orders/user/4
    public ResponseEntity<?> getOrders(@Valid @PathVariable("user_id") int userId) {
        try {
            List<OrderEntity> orders = orderService.findByUserId(userId);
            List<OrderResponse> orderResponses = orders.stream().map(OrderResponse::formOrderResponse).toList();
            return ResponseEntity.ok(orderResponses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{order_id}")
    public ResponseEntity<?> updateOrder(@PathVariable int order_id,
                                              @Valid @RequestBody OrderDTO orderDTO,
                                              BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            OrderEntity updatedOrder = orderService.updateOrder(order_id, orderDTO);
            return ResponseEntity.ok(OrderResponse.formOrderResponse(updatedOrder));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/status/{order_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateOrderStatus(@PathVariable("order_id") int orderId,
                                               @RequestBody UpdateOrderStatusDTO statusDTO) {
        try {
            if (statusDTO.getStatus() == null) {
                return ResponseEntity.badRequest().body("Status is not null");
            }
            OrderEntity order = orderService.updateOrderStatus(orderId, statusDTO.getStatus());
            return ResponseEntity.ok(OrderResponse.formOrderResponse(order));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{order_id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("order_id") int orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok(localizationUtils.getLocalizedMessage(MessageKeys.DELETE_ORDER_SUCCESSFULLY));
    }

    @GetMapping("/keyword")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOrdersByKeyword(@RequestParam(defaultValue = "", required = false) String keyword,
                                                @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("id").ascending());
        Page<OrderEntity> pageOrders = orderService.searchOrders(keyword, pageRequest);
        OrdersListResponse ordersListResponse = new OrdersListResponse();
        ordersListResponse.setOrders(pageOrders.stream().map(OrderResponse::formOrderResponse).toList());
        ordersListResponse.setTotalPages(pageOrders.getTotalPages());
        return ResponseEntity.ok(ordersListResponse);
    }
}
