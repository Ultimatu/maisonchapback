package com.tonde.maisonchapback.models.workflows;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "_type_abonnement")
@Schema(
        name = "TypeAbonnement",
        description = "TypeAbonnement model",
        requiredMode = Schema.RequiredMode.REQUIRED,
        implementation = TypeAbonnement.class,
        example = """
                {
                  "id": "integer",
                  "type": "string",
                  "description": "string",
                  "access": "string"
                }""",
        requiredProperties = {"id", "type", "description", "access"}
)
public class TypeAbonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String access;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "created_at")
    private java.time.LocalDateTime dateCreation;

    @Column(columnDefinition = "TIMESTAMP", name = "updated_at")
    private java.time.LocalDateTime updatedAt;


}
