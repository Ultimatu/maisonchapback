package com.tonde.maisonchapback.models.workflows;


import com.tonde.maisonchapback.models.workflows.user.User;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "abonnement")
@OpenAPI31

public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_abonnement_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_TYPE_ABONNEMENT_ID_Abonnement"))
    private TypeAbonnement typeAbonnement;

    @Column(nullable = false)
    private String duree;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String etat;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "date_debut")
    private LocalDateTime dateDebut;

    @Column(columnDefinition = "TIMESTAMP", name = "date_fin")
    private LocalDateTime dateFin;

    @Column(nullable = false , columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "created_at")
    private LocalDateTime dateCreation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID_Abonnement"))
    private User user;

}
