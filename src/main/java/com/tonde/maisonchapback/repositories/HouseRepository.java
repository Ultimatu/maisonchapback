package com.tonde.maisonchapback.repositories;

import com.tonde.maisonchapback.models.workflows.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository  extends JpaRepository<House, Integer> {

}
