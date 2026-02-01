package com.medlibrary.domain.entity;

import com.medlibrary.domain.enums.Severity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "interactions",
        indexes = {
                @Index(name = "idx_interaction_med_a", columnList = "medication_a_id"),
                @Index(name = "idx_interaction_med_b", columnList = "medication_b_id"),
                @Index(name = "idx_interaction_severity", columnList = "severity")
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_interaction_med_a_med_b",
                        columnNames = {"medication_a_id", "medication_b_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interaction {

    @Id
    @GeneratedValue
    private UUID id;

    /* =========================
               Relations
       ========================= */

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "medication_a_id", nullable = false)
    private Medication medicationA;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "medication_b_id", nullable = false)
    private Medication medicationB;

    /* =========================
              Attributes
       ========================= */

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Severity severity;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

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
