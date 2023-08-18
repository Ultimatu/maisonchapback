package com.tonde.maisonchapback.models.workflows;


import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "_type_abonnement")
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

    @Column(nullable = false , columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "created_at")
    private java.time.LocalDateTime dateCreation;

    @Column(columnDefinition = "TIMESTAMP", name = "updated_at")
    private java.time.LocalDateTime updatedAt;


}
