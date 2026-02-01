package com.medlibrary.repository;

import com.medlibrary.domain.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DiseaseRepository extends JpaRepository<Disease, UUID> {

    Optional<Disease> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);
}