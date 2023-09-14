package com.tonde.maisonchapback.models.workflows;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "_photos")
@Schema(
        name = "Photo",
        description = "Photo model",
        requiredMode = Schema.RequiredMode.REQUIRED,
        implementation = Photo.class,
        example = """
                {
                  "id": "integer",
                  "house": "House",
                  "url": "string",
                  "description": "string"
                }""",
        requiredProperties = {"id", "house", "url", "description"}
)
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_HOUSE_ID_Photo"))
    private House house;


    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String description;


}
