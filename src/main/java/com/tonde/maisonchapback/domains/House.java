package com.tonde.maisonchapback.domains;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
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
public class House implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID"))
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_TYPE_ID"))
    private TypeHouse typeHouse;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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


    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Photo> photos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TypeHouse getTypeHouse() {
        return typeHouse;
    }

    public void setTypeHouse(TypeHouse typeHouse) {
        this.typeHouse = typeHouse;
    }

    public Status getStatusHouse() {
        return statusHouse;
    }

    public void setStatusHouse(Status statusHouse) {
        this.statusHouse = statusHouse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(String numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public String getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(String numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getDisponibility() {
        return disponibility;
    }

    public void setDisponibility(String disponibility) {
        this.disponibility = disponibility;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
