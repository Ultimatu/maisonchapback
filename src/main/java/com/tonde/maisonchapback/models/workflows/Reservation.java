package com.tonde.maisonchapback.models.workflows;

import com.tonde.maisonchapback.models.workflows.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "reservation")
@Schema(
        name = "Reservation",
        description = "Reservation model",
        requiredMode = Schema.RequiredMode.REQUIRED,
        implementation = Reservation.class,
        example = """
                {
                  "id": "integer",
                  "house": "House",
                  "user": "User",
                  "startDate": "string",
                  "endDate": "string",
                  "status": "string"
                }""",
        requiredProperties = {"id", "house", "user", "startDate", "endDate", "status"}
)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_house", referencedColumnName = "id")
    private House house;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_RESERVATION"))
    private User user;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;


    @Column(name = "status")
    private String status;
}
