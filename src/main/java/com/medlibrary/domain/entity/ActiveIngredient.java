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
        name = "active_ingredients",
        indexes = {
                @Index(name = "idx_active_ingredient_name", columnList = "name")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActiveIngredient {

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

    /* =========================
               Relations
       ========================= */

    @ManyToMany(mappedBy = "activeIngredients")
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
