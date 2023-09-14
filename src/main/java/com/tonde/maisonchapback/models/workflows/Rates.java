package com.tonde.maisonchapback.models.workflows;


import com.tonde.maisonchapback.models.workflows.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_rates")
@Schema(
        name = "Rates",
        description = "Rates model",
        requiredMode = Schema.RequiredMode.REQUIRED,
        implementation = Rates.class,
        example = """
                {
                  "id": "integer",
                  "rate": "string",
                  "description": "string",
                  "house": "House",
                  "user": "User"
                }""",
        requiredProperties = {"id", "rate", "description", "house", "user"}
)
public class Rates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String rate;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_HOUSE_ID_Rates"))
    private House house;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID_Rates"))
    private User user;
}
