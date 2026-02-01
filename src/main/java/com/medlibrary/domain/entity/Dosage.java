package com.medlibrary.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "dosages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dosage {

    @Id
    @GeneratedValue
    private UUID id;

    /* =========================
              Attributes
       ========================= */

    @NotBlank
    @Size(max = 50)
    @Column(name = "age_group", nullable = false, length = 50)
    private String ageGroup;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String dose;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String frequency;

    @NotBlank
    @Size(max = 100)
    @Column(name = "max_daily_dose", nullable = false, length = 100)
    private String maxDailyDose;

    /* =========================
               Relations
       ========================= */

    @ManyToOne(optional = false)
    @JoinColumn(name = "medication_id", nullable = false)
    private Medication medication;
}
