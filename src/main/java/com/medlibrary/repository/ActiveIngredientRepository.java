package com.medlibrary.repository;

import com.medlibrary.domain.entity.ActiveIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ActiveIngredientRepository extends JpaRepository<ActiveIngredient, UUID> {

    Optional<ActiveIngredient> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);
}