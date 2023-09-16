package com.tonde.maisonchapback.domains;

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
public class Favoris {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_HOUSE_ID_Favoris"))
    private House house;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID_Favoris"))
    private User user;


}
