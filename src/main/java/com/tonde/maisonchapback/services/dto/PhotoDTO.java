package com.tonde.maisonchapback.services.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;



public class PhotoDTO implements Serializable {

    private Integer id;
    @NotNull
    private String url;

    @NotNull
    private String description;

    private HouseDTO house;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public HouseDTO getHouse() {
        return house;
    }

    public void setHouse(HouseDTO house) {
        this.house = house;
    }
}
