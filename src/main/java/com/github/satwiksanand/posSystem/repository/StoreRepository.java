package com.github.satwiksanand.posSystem.repository;

import com.github.satwiksanand.posSystem.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByStoreAdminId(Long id);
}
