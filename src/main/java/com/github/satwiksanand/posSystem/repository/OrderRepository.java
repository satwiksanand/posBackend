package com.github.satwiksanand.posSystem.repository;

import com.github.satwiksanand.posSystem.models.Order;
import com.github.satwiksanand.posSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByBranchId(Long branchId);
    List<Order> findByCashierId(Long cashierId);
    List<Order> findByBranchIdAndCreatedAtBetween(Long branchId, LocalDateTime start, LocalDateTime end);
    List<Order> findByCashierAndCreatedAtBetween(User cashier, LocalDateTime start, LocalDateTime end);
    List<Order> findTop5ByBranchIdOrderByCreatedAtDesc(Long branchId);
}
