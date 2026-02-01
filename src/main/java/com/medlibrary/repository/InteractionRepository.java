package com.medlibrary.repository;

import com.medlibrary.domain.entity.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InteractionRepository extends JpaRepository<Interaction, UUID> {

    @Query("""
           SELECT i
           FROM Interaction i
           WHERE i.medicationA.id = :medicationId
              OR i.medicationB.id = :medicationId
           """)
    List<Interaction> findByMedicationId(
            @Param("medicationId") UUID medicationId
    );

    @Query("""
           SELECT i
           FROM Interaction i
           WHERE i.medicationA.id = :medA
             AND i.medicationB.id = :medB
           """)
    Optional<Interaction> findByMedicationPair(
            @Param("medA") UUID medicationAId,
            @Param("medB") UUID medicationBId
    );
}