package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Rates;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Hidden
public interface RateService {

    ResponseEntity<?> addRate(Rates rate);

    ResponseEntity<?> updateRate(Rates rates);

    ResponseEntity<?> deleteRate(int id);

    Rates getRateById(int id);

    List<Rates> getRateByHouseId(int houseId);

    List<Rates> getRateByUserId(int userId);

    Rates getRateByHouseIdAndUserId(int houseId, int userId);

    List<Rates> getAllRates();

}
