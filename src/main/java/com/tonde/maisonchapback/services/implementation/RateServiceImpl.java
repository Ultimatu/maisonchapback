package com.tonde.maisonchapback.services.implementation;

import com.tonde.maisonchapback.models.workflows.House;
import com.tonde.maisonchapback.models.workflows.Rates;
import com.tonde.maisonchapback.models.workflows.user.User;
import com.tonde.maisonchapback.repositories.HouseRepository;
import com.tonde.maisonchapback.repositories.RateRepository;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.services.interfaces.RateService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Hidden
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;
    private final UserRepository userRepository;
    private final HouseRepository houseRepository;

    @Override
    public ResponseEntity<?> addRate(Rates rate) {
        try {
            rateRepository.save(rate);
            return ResponseEntity.ok("Rate added");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Rate not added");
        }

    }

    @Override
    public ResponseEntity<?> updateRate(Rates rate) {
        Optional<Rates> optionalRates = rateRepository.findById(rate.getId());
        if (optionalRates.isPresent()) {
            Rates rates = optionalRates.get();
            rates.setRate(rate.getRate());
            rates.setDescription(rate.getDescription());
            rateRepository.save(rates);
            return ResponseEntity.ok("rate updated");
        }
        return ResponseEntity.badRequest().body("Impossible to update this rate");


    }

    @Override
    public ResponseEntity<?> deleteRate(int id) {

        Optional<Rates> optionalRates = rateRepository.findById(id);
        if (optionalRates.isPresent()) {
            rateRepository.delete(optionalRates.get());
            return ResponseEntity.ok("rate deleted");
        }

        return ResponseEntity.badRequest().body("Impossible to delete this rate");
    }

    @Override
    public Rates getRateById(int id) {
        Optional<Rates> optionalRates = rateRepository.findById(id);
        return optionalRates.orElse(null);
    }

    @Override
    public List<Rates> getRateByHouseId(int id) {
        Optional<House> optionalHouse = houseRepository.findById(id);
        if (optionalHouse.isPresent()) {
            House house = optionalHouse.get();
            return rateRepository.findAllByHouse(house);
        }

        return Collections.emptyList();
    }

    @Override
    public List<Rates> getRateByUserId(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return rateRepository.findAllByUser(user);
        }
        return Collections.emptyList();
    }

    @Override
    public Rates getRateByHouseIdAndUserId(int houseId, int userId) {
        Optional<House> optionalHouse = houseRepository.findById(houseId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalHouse.isPresent() && optionalUser.isPresent()) {
            House house = optionalHouse.get();
            User user = optionalUser.get();
            return rateRepository.findByHouseAndUser(house, user);
        }
        return null;
    }

    @Override
    public List<Rates> getAllRates() {
        return rateRepository.findAll();
    }


}
