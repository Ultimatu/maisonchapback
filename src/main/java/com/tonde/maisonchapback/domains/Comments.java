package com.tonde.maisonchapback.domains;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "_comments")
@Schema(
        name = "Comments",
        description = "Comments model",
        requiredMode = Schema.RequiredMode.REQUIRED,
        implementation = Comments.class,
        example = """
                {
                  "id": "integer",
                  "house": "House",
                  "user": "User",
                  "comment": "string",
                  "dateCreation": "LocalDateTime",
                  "dateModification": "LocalDateTime"
                }""",
        requiredProperties = {"id", "house", "user", "comment", "dateCreation"}

)
public class Comments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(
            value = {"user", "typeHouse", "status", "comments", "photos", "reservations", "rates", "statistiques", "favoris"},
            allowSetters = true
    )
    @JoinColumn(name = "house_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_HOUSE_ID_Comment"))
    private House house;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(
            value = {"abonnements", "houses", "comments", "reservations", "rates", "statistiques", "favoris", "messages"},
            allowSetters = true
    )
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID_Comment"))
    private User user;


    @Column(nullable = false)
    private String comment;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "created_at")
    private Instant dateCreation;

    @Column(columnDefinition = "TIMESTAMP", name = "date_modification")
    private Instant dateModification;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
