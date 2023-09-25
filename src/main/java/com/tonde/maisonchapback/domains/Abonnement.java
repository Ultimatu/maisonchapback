package com.tonde.maisonchapback.domains;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;

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
public class Abonnement implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(
            value = {"abonnements", "houses", "comments", "reservations", "rates", "statistiques", "favoris", "messages"},
            allowSetters = true
    )
    @JoinColumn(name = "type_abonnement_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_TYPE_ABONNEMENT_ID_Abonnement"))
    private TypeAbonnement typeAbonnement;

    @Column(nullable = true)
    private String duree;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String etat;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "date_debut")
    private Instant dateDebut;
    @Column(columnDefinition = "TIMESTAMP", name = "date_fin")
    private Instant dateFin;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "created_at")
    private Instant dateCreation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID_Abonnement"))
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeAbonnement getTypeAbonnement() {
        return typeAbonnement;
    }

    public void setTypeAbonnement(TypeAbonnement typeAbonnement) {
        this.typeAbonnement = typeAbonnement;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Instant getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Instant getDateFin() {
        return dateFin;
    }

    public void setDateFin(Instant dateFin) {
        this.dateFin = dateFin;
    }

    public Instant getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}



