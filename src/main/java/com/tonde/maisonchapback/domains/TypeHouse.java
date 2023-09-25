package com.tonde.maisonchapback.domains;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
public class TypeHouse implements Serializable {


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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Instant getDateModification() {
        return dateModification;
    }

    public void setDateModification(Instant dateModification) {
        this.dateModification = dateModification;
    }
}
