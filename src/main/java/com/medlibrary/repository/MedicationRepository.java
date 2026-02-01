package com.medlibrary.repository;

import com.medlibrary.domain.entity.Medication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface MedicationRepository extends JpaRepository<Medication, UUID> {

    /* =========================
             Name searches
       ========================= */

    Page<Medication> findByCommercialNameContainingIgnoreCase(
            String commercialName,
            Pageable pageable
    );

    Page<Medication> findByGenericNameContainingIgnoreCase(
            String genericName,
            Pageable pageable
    );

    /* =========================
           Combined Search
       ========================= */

    @Query("""
           SELECT m
           FROM Medication m
           WHERE
               LOWER(m.commercialName) LIKE LOWER(CONCAT('%', :query, '%'))
               OR LOWER(m.genericName) LIKE LOWER(CONCAT('%', :query, '%'))
           """)
    Page<Medication> searchByName(
            @Param("query") String query,
            Pageable pageable
    );

    /* =========================
          Filter by relations
       ========================= */

    @Query("""
           SELECT DISTINCT m
           FROM Medication m
           JOIN m.diseases d
           WHERE LOWER(d.name) = LOWER(:diseaseName)
           """)
    Page<Medication> findByDiseaseName(
            @Param("diseaseName") String diseaseName,
            Pageable pageable
    );

    @Query("""
           SELECT DISTINCT m
           FROM Medication m
           JOIN m.activeIngredients ai
           WHERE LOWER(ai.name) = LOWER(:ingredientName)
           """)
    Page<Medication> findByActiveIngredientName(
            @Param("ingredientName") String ingredientName,
            Pageable pageable
    );
}
