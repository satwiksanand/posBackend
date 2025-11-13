package com.github.satwiksanand.posSystem.controller;

import com.github.satwiksanand.posSystem.domain.OrderStatus;
import com.github.satwiksanand.posSystem.domain.PaymentType;
import com.github.satwiksanand.posSystem.payload.dto.OrderDto;
import com.github.satwiksanand.posSystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws Exception {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<OrderDto>> getOrdersByBranch(@PathVariable Long id,
                                                            @RequestParam(required = false) Long customerId,
                                                            @RequestParam(required = false) Long cashierId,
                                                            @RequestParam(required = false) PaymentType paymentType,
                                                            @RequestParam(required = false) OrderStatus orderStatus) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByBranch(id, customerId, cashierId, paymentType, orderStatus));
    }

    @GetMapping("/cashier/{id}")
    public ResponseEntity<List<OrderDto>> getOrderByCashier(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrderByCashier(id));
    }

    @GetMapping("/today/branch/{id}")
    public ResponseEntity<List<OrderDto>> getTodayOrder(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getTodayOrdersByBranch(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDto>> getCustomersOrder(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(id));
    }

    @GetMapping("/recent/{id}")
    public ResponseEntity<List<OrderDto>> getRecentOrder(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getTop5RecentOrderByBranchId(id));
    }
}
