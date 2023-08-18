package com.tonde.maisonchapback.services.interfaces;

import com.tonde.maisonchapback.models.workflows.Photo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public interface PhotoService {

    public ResponseEntity<?> addPhotos(List<MultipartFile> photoFiles, Photo photo) throws IOException;

    public ResponseEntity<?> updatePhoto(Photo photo, MultipartFile photoFile);

    public ResponseEntity<?> deletePhoto(int id);

    public Photo getPhotoById(int id);

    public List<Photo> getAllPhotos();

    public List<Photo> getAllPhotosByHouseId(int houseId);

    public ResponseEntity<?> getAllByHouseUser(int userId);


}
