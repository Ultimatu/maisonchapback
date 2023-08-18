package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Rates;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RateService {

    public ResponseEntity<?> addRate(Rates rate);

    public ResponseEntity<?> updateRate(Rates rates);

    public ResponseEntity<?> deleteRate(int id);

    public Rates getRateById(int id);

    public List<Rates> getRateByHouseId(int houseId);

    public List<Rates> getRateByUserId(int userId);

    public Rates getRateByHouseIdAndUserId(int houseId, int userId);

    public List<Rates> getAllRates();

}
