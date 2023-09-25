package com.tonde.maisonchapback.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "_favoris")
@Schema(
        name = "Favoris",
        description = "Favoris model",
        requiredMode = Schema.RequiredMode.REQUIRED,
        implementation = Favoris.class,
        example = """
                {
                  "id": "integer",
                  "house": "House",
                  "user": "User"
                }""",

        requiredProperties = {"id", "house", "user"}
)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Favoris implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(
            value = {"user", "typeHouse", "status", "comments", "photos", "reservations", "rates", "statistiques", "favoris"},
            allowSetters = true
    )
    @JoinColumn(name = "house_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_HOUSE_ID_Favoris"))
    private House house;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(
            value = {"abonnements", "houses", "comments", "reservations", "rates", "statistiques", "favoris", "messages"},
            allowSetters = true
    )
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID_Favoris"))
    private User user;


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
}
