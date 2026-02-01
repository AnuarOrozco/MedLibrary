package com.medlibrary.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(
        name = "diseases",
        indexes = {
                @Index(name = "idx_disease_name", columnList = "name"),
                @Index(name = "idx_disease_icd_code", columnList = "icd_code")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disease {

    @Id
    @GeneratedValue
    private UUID id;

    /* =========================
              Attributes
       ========================= */

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150, unique = true)
    private String name;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Size(max = 20)
    @Column(name = "icd_code", length = 20)
    private String icdCode;

    /* =========================
               Relations
       ========================= */

    @ManyToMany(mappedBy = "diseases")
    private Set<Medication> medications = new HashSet<>();

    /* =========================
                 Audit
       ========================= */

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    /* =========================
          Lifecycle callbacks
       ========================= */

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
