package com.tonde.maisonchapback.domains;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "abonnement")
@Schema(
        name = "Abonnement",
        description = "Abonnement model",
        example = """
                {
                  "id": "integer",
                  "typeAbonnement": "TypeAbonnement",
                  "duree": "string",
                  "type": "string",
                  "etat": "string",
                  "dateDebut": "LocalDateTime",
                  "dateFin": "LocalDateTime",
                  "dateCreation": "LocalDateTime",
                  "user": "User"
                }""",
        requiredProperties = {"typeAbonnement", "duree", "type", "etat", "dateDebut", "dateFin", "dateCreation", "user"}
)
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_abonnement_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_TYPE_ABONNEMENT_ID_Abonnement"))
    private TypeAbonnement typeAbonnement;

    @Column(nullable = true)
    private String duree;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String etat;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "date_debut")
    private LocalDateTime dateDebut;
    @Column(columnDefinition = "TIMESTAMP", name = "date_fin")
    private LocalDateTime dateFin;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "created_at")
    private LocalDateTime dateCreation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID_Abonnement"))
    private User user;

}



