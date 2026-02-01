package com.medlibrary.repository;

import com.medlibrary.domain.entity.Dosage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DosageRepository extends JpaRepository<Dosage, UUID> {

    List<Dosage> findByMedicationId(UUID medicationId);
}