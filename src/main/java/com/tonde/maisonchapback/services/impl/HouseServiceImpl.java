package com.tonde.maisonchapback.services.impl;

import com.tonde.maisonchapback.domains.House;
import com.tonde.maisonchapback.domains.Status;
import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.exceptions.CustomLogger;
import com.tonde.maisonchapback.repositories.HouseRepository;
import com.tonde.maisonchapback.repositories.StatusRepository;
import com.tonde.maisonchapback.repositories.UserRepository;
import com.tonde.maisonchapback.services.interfaces.HouseService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Hidden
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;


    private final Logger logger = Logger.getLogger(HouseServiceImpl.class.getName());

    @Override
    public List<House> getAllHouses() {
        logger.info("Getting all houses");

        /*
        List<HouseDTO> houseDTOS = new ArrayList<>();
         *
        if (houses.isEmpty()) {
            return Collections.emptyList();
        } else {

            for (House house : houses) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(house.getUser().getId());
                userDTO.setNom(house.getUser().getNom());

                StatusDTO statusDTO = new StatusDTO();
                statusDTO.setId(house.getStatusHouse().getId());

                TypeHouseDTO typeHouseDTO = new TypeHouseDTO();
                typeHouseDTO.setId(house.getTypeHouse().getId());

                List<PhotoDTO> photoDTOS = new ArrayList<>();
                for (Photo photo : house.getPhotos()) {
                    PhotoDTO photoDTO = new PhotoDTO();
                    photoDTO.setId(photo.getId());
                    photoDTO.setUrl(photo.getUrl());
                    photoDTOS.add(photoDTO);
                }

                HouseDTO houseDTO = new HouseDTO();
                houseDTO.setId(house.getId());
                houseDTO.setAddress(house.getAddress());
                houseDTO.setCity(house.getCity());
                houseDTO.setCountry(house.getCountry());
                houseDTO.setNumberOfBathrooms(house.getNumberOfBathrooms());
                houseDTO.setTitle(house.getTitle());
                houseDTO.setSurface(house.getSurface());
                houseDTO.setStatus(statusDTO);
                houseDTO.setTypeHouse(typeHouseDTO);
                houseDTO.setNumberOfRooms(house.getNumberOfRooms());
                houseDTO.setPrice(house.getPrice());
                houseDTO.setDescription(house.getDescription());
                houseDTO.setNumberOfFloors(house.getNumberOfFloors());
                houseDTO.setUserDTO(userDTO);
                houseDTO.setCountry(house.getCountry());
                houseDTO.setDisponibility(house.getDisponibility());
                houseDTO.setPhotos(photoDTOS);

                houseDTOS.add(houseDTO);
            }
            }*/

            return houseRepository.findAll();

    }

    @Override
    public House getHouseById(int id) {

        Optional<House> houseOptional = houseRepository.findById(id);
        return houseOptional.orElse(null);

    }

    @Override
    public ResponseEntity<String> addHouse(House house) {

        logger.info("Adding house");
        houseRepository.save(house);
        return ResponseEntity.ok("Maison ajoutée avec succès");

    }

    @Override
    public ResponseEntity<String> updateHouse(House house) {
        Optional<House> houseOptional = houseRepository.findById(house.getId());
        if (houseOptional.isPresent()) {
            houseOptional.get().setAddress(house.getAddress());
            houseOptional.get().setCity(house.getCity());
            houseOptional.get().setCountry(house.getCountry());
            houseOptional.get().setNumberOfBathrooms(house.getNumberOfBathrooms());
            houseOptional.get().setTitle(house.getTitle());
            houseOptional.get().setSurface(house.getSurface());
            houseOptional.get().setStatusHouse(house.getStatusHouse());
            houseOptional.get().setTypeHouse(house.getTypeHouse());
            houseOptional.get().setNumberOfRooms(house.getNumberOfRooms());
            houseOptional.get().setPrice(house.getPrice());
            houseOptional.get().setDescription(house.getDescription());
            houseOptional.get().setNumberOfFloors(house.getNumberOfFloors());
            houseOptional.get().setUser(house.getUser());
            houseOptional.get().setCountry(house.getCountry());
            houseOptional.get().setDisponibility(house.getDisponibility());
            houseRepository.save(houseOptional.get());
            return ResponseEntity.ok("Maison modifiée avec succès");
        } else {
            return ResponseEntity.badRequest().body("Maison non trouvée");
        }

    }

    @Override
    public ResponseEntity<String> deleteHouse(int id) {
        Optional<House> houseOptional = houseRepository.findById(id);
        if (houseOptional.isPresent()) {
            houseRepository.delete(houseOptional.get());
            return ResponseEntity.ok("Maison supprimée avec succès");
        } else {
            return ResponseEntity.badRequest().body("Maison non trouvée");
        }

    }

    @Override
    public List<House> getAllRentingHouses() {
        CustomLogger.log("INFO", "Getting all renting houses");
        Optional<Status> status = statusRepository.findById(1);
        return houseRepository.findAllHouseByStatusHouse(status.orElseThrow());
    }

    @Override
    public List<House> getAllSellingHouses() {
        Optional<Status> status;
        CustomLogger.log("INFO", "Getting all selling houses");
        status = statusRepository.findById(2);
        return houseRepository.findAllHouseByStatusHouse(status.orElseThrow());
    }

    @Override
    public List<House> getAllHousesByUserId(int userId) {
        logger.info("Getting user houses");
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(houseRepository::findAllByUser).orElseThrow(null);

    }

    @Override
    public List<House> getAllHousesByStatus(int status) {
        Optional<Status> optional = statusRepository.findById(status);
        return optional.map(houseRepository::findAllHouseByStatusHouse).orElseThrow(null);
    }

    @Override
    public List<House> getAllHousesByDisponibility(String disponibility) {
        List<House> houses = houseRepository.findAllByDisponibility(disponibility);
        if (houses.isEmpty()) {
            return Collections.emptyList();
        } else {
            return houses;
        }
    }
}
