package com.tonde.maisonchapback.domains;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "_photos")
@Schema(
        name = "Photo",
        description = "Photo model",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = """
                {
                  "id": "integer",
                  "house": "House",
                  "url": "string",
                  "description": "string"
                }""",
        requiredProperties = {"id", "house", "url", "description"}
)
public class Photo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_HOUSE_ID_Photo"))
    private House house;


    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String description;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
