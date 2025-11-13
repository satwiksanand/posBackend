package com.github.satwiksanand.posSystem.service.impl;

import com.github.satwiksanand.posSystem.domain.OrderStatus;
import com.github.satwiksanand.posSystem.domain.PaymentType;
import com.github.satwiksanand.posSystem.mapper.OrderMapper;
import com.github.satwiksanand.posSystem.models.*;
import com.github.satwiksanand.posSystem.payload.dto.OrderDto;
import com.github.satwiksanand.posSystem.repository.OrderRepository;
import com.github.satwiksanand.posSystem.repository.ProductRepository;
import com.github.satwiksanand.posSystem.service.OrderService;
import com.github.satwiksanand.posSystem.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws Exception {
        User cashier = userService.getCurrentUser();
        Branch branch = cashier.getBranch();
        if(branch == null){
            throw new Exception("cashier's branch not found!");
        }
        Order order = Order.builder()
                .branch(branch)
                .cashier(cashier)
                .customer(orderDto.getCustomer())
                .paymentType(orderDto.getPaymentType())
                .build();

        List<OrderItem> orderItems = orderDto.getItems().stream().map(
                orderItemDto -> {
                    Product product = productRepository.findById(orderItemDto.getProductId()).orElseThrow(
                            () -> new EntityNotFoundException("product not found!")
                    );
                    return OrderItem.builder()
                            .product(product)
                            .quantity(orderItemDto.getQuantity())
                            .price(product.getSellingPrice() * orderItemDto.getQuantity())
                            .order(order)
                            .build();
                }
        ).toList();
        double total = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
        order.setTotalAmount(total);
        order.setItems(orderItems);
        return OrderMapper.toDto(order);
    }

    @Override
    public OrderDto getOrderById(Long id) throws Exception {
        return orderRepository.findById(id).map(OrderMapper::toDto).orElseThrow(
                ()->new Exception("order not found with id : " + id)
        );
    }

    @Override
    public List<OrderDto> getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus orderStatus) {
        return orderRepository.findByBranchId(branchId).stream()
                .filter(order -> customerId == null || (order.getCustomer() != null && Objects.equals(order.getCustomer().getId(), customerId)))
                .filter(order -> cashierId == null || (order.getCashier() != null && Objects.equals(order.getCashier().getId(), cashierId)))
                .filter(order -> paymentType == null || (order.getPaymentType() != null && order.getPaymentType() == paymentType))
                .map(OrderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderDto> getOrderByCashier(Long cashierId) {
        return orderRepository.findByCashierId(cashierId).stream().map(OrderMapper::toDto).toList();
    }

    @Override
    public void deleteOrder(Long orderId) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new Exception("order not found!")
        );
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getTodayOrdersByBranch(Long branchId) {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();
        return orderRepository.findByBranchIdAndCreatedAtBetween(branchId, start, end).stream().map(OrderMapper::toDto).toList();
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream().map(OrderMapper::toDto).toList();
    }

    @Override
    public List<OrderDto> getTop5RecentOrderByBranchId(Long branchId) {
        return orderRepository.findTop5ByBranchIdOrderByCreatedAtDesc(branchId).stream().map(OrderMapper::toDto).toList();
    }
}
