package com.project.shopapp.controller;

import com.project.shopapp.Responses.OrderDetailResponse;
import com.project.shopapp.Responses.OrderResponse;
import com.project.shopapp.dto.OrderDetailDTO;
import com.project.shopapp.dto.*;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.model.OrderDetailEntity;
import com.project.shopapp.repositories.OrderDetailRepository;
import com.project.shopapp.services.servicesImp.OrderDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order_details")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    //Thêm mới 1 order detail
    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(
            @Valid  @RequestBody OrderDetailDTO newOrderDetail,
            BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result
                        .getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            OrderDetailEntity orderDetail = orderDetailService.createOrderDetail(newOrderDetail);
            return ResponseEntity.ok(OrderDetailResponse.fromOrderDetail(orderDetail));
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(@PathVariable("id") int id) {
        try {
            OrderDetailEntity orderDetail = orderDetailService.getOrderDetail(id);
            return ResponseEntity.ok(OrderDetailResponse.fromOrderDetail(orderDetail));
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    //lấy ra danh sách các order_details của 1 order nào đó
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(@PathVariable("orderId") int orderId) {
        List<OrderDetailResponse> listOrderDetails = orderDetailService
                .findByOrderId(orderId).stream().map(OrderDetailResponse::fromOrderDetail).toList();
        return ResponseEntity.ok(listOrderDetails);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(
            @PathVariable("id") int id,
            @Valid @RequestBody OrderDetailDTO orderDetailDTO,
            BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result
                        .getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            OrderDetailEntity updatedOrderDetail = orderDetailService.updateOrderDetail(id, orderDetailDTO);
            return ResponseEntity.ok(OrderDetailResponse.fromOrderDetail(updatedOrderDetail));
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderDetail(@PathVariable("id") int id) {
        orderDetailService.deleteById(id);
        return ResponseEntity.ok("Delete order detail success");
    }
}
