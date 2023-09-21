package com.tonde.maisonchapback.services;


import com.tonde.maisonchapback.exceptions.CustomLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class is responsible for loading a file as a resource from the specified upload directory.
 */
@Service
public class RessourceLoader{

    @Value("${file.upload-dir}")
    private String uploadPath;


    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = Paths.get(uploadPath).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                CustomLogger.log("ERROR", "File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            CustomLogger.log("ERROR", "File not found " + fileName);
        }
        return null;

    }
}
