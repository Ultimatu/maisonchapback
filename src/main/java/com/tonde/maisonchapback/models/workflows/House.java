package com.tonde.maisonchapback.models.workflows;


import com.tonde.maisonchapback.models.workflows.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_houses")
@Schema(
        name = "House",
        description = "House model",
        requiredMode = Schema.RequiredMode.REQUIRED,
        implementation = House.class,
        example = """
                {
                  "id": "integer",
                  "user": "User",
                  "typeHouse": "TypeHouse",
                  "statusHouse": "Status",
                  "title": "string",
                  "description": "string",
                  "address": "string",
                  "city": "string",
                  "country": "string",
                  "numberOfRooms": "string",
                  "numberOfBathrooms": "string",
                  "numberOfFloors": "string",
                  "price": "string",
                  "surface": "string",
                  "disponibility": "string",
                  "photos": "List<Photo>"
                }""",
        requiredProperties = {"id", "user", "typeHouse", "statusHouse", "title", "description", "address", "city", "country", "numberOfRooms", "numberOfBathrooms", "numberOfFloors", "price", "surface", "disponibility", "photos"}
)
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //user_id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_TYPE_ID"))
    private TypeHouse typeHouse;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_STATUS_ID"))
    private Status statusHouse;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String numberOfRooms;

    @Column(nullable = false)
    private String numberOfBathrooms;

    @Column(nullable = false)
    private String numberOfFloors;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String surface;

    @Column(nullable = false)
    private String disponibility;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Photo> photos;

}
