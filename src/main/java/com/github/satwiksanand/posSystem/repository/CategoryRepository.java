package com.github.satwiksanand.posSystem.repository;

import com.github.satwiksanand.posSystem.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByStoreId(Long storeId);
}
