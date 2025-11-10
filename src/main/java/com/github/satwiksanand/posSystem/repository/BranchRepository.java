package com.github.satwiksanand.posSystem.repository;

import com.github.satwiksanand.posSystem.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByStoreId(Long id);
}
