package com.github.satwiksanand.posSystem.repository;

import com.github.satwiksanand.posSystem.models.Store;
import com.github.satwiksanand.posSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByStore(Store store);
    List<User> findByBranchId(Long branchId);
}
