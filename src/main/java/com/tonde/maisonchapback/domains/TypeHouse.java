package com.tonde.maisonchapback.domains;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityScan
@Table(name = "_type_house")
@Schema(
        name = "TypeHouse",
        description = "TypeHouse model",
        requiredMode = Schema.RequiredMode.REQUIRED,
        implementation = TypeHouse.class,
        example = """
                {
                  "id": "integer",
                  "type": "string",
                  "description": "string"
                }""",
        requiredProperties = {"id", "type", "description"}
)
public class TypeHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "created_at")
    private Instant dateCreation;

    @Column(columnDefinition = "TIMESTAMP", name = "updated_at")
    private Instant dateModification;


}
