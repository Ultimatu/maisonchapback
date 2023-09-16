package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.exceptions.ApiError;
import com.tonde.maisonchapback.domains.Photo;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@Hidden
public interface PhotoService {

    ResponseEntity<?> addPhotos(List<MultipartFile> photoFiles, int houseId) throws IOException;

    ResponseEntity<?> updatePhoto(Photo photo, MultipartFile photoFile) throws RuntimeException, ApiError;

    ResponseEntity<?> deletePhoto(int id);

    Photo getPhotoById(int id);

    List<Photo> getAllPhotos();

    List<Photo> getAllPhotosByHouseId(int houseId);

    ResponseEntity<?> getAllByHouseUser(int userId);


}
