package com.github.satwiksanand.posSystem.service;

import com.github.satwiksanand.posSystem.domain.OrderStatus;
import com.github.satwiksanand.posSystem.domain.PaymentType;
import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.payload.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto) throws Exception;
    OrderDto getOrderById(Long id) throws Exception;
//    OrderDto updateOrder(Long id, OrderDto orderDto);
    List<OrderDto> getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus orderStatus);
    List<OrderDto> getOrderByCashier(Long cashierId);
    void deleteOrder(Long orderId) throws Exception;
    List<OrderDto> getTodayOrdersByBranch(Long branchId);
    List<OrderDto> getOrdersByCustomerId(Long customerId);
    List<OrderDto> getTop5RecentOrderByBranchId(Long branchId);
}
