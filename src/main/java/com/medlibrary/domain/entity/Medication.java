package com.medlibrary.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(
        name = "medications",
        indexes = {
                @Index(name = "idx_medication_commercial_name", columnList = "commercial_name"),
                @Index(name = "idx_medication_generic_name", columnList = "generic_name")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medication {

    @Id
    @GeneratedValue
    private UUID id;

    /* =========================
               Attributes
       ========================= */

    @NotBlank
    @Size(max = 150)
    @Column(name = "commercial_name", nullable = false, length = 150)
    private String commercialName;

    @NotBlank
    @Size(max = 150)
    @Column(name = "generic_name", nullable = false, length = 150)
    private String genericName;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Size(max = 500)
    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String indications;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contraindications;

    @PositiveOrZero
    @Column(name = "price_estimate", precision = 10, scale = 2)
    private BigDecimal priceEstimate;

    /* =========================
               Relations
       ========================= */

    @ManyToMany
    @JoinTable(
            name = "medication_disease",
            joinColumns = @JoinColumn(name = "medication_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private Set<Disease> diseases = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "medication_active_ingredient",
            joinColumns = @JoinColumn(name = "medication_id"),
            inverseJoinColumns = @JoinColumn(name = "active_ingredient_id")
    )
    private Set<ActiveIngredient> activeIngredients = new HashSet<>();

    @OneToMany(
            mappedBy = "medication",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Dosage> dosages = new HashSet<>();

    @OneToMany(
            mappedBy = "medicationA"
    )
    private Set<Interaction> interactions = new HashSet<>();

    /* =========================
                 Audit
       ========================= */

    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @NotNull
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
