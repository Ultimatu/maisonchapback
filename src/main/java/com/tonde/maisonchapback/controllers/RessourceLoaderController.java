package com.tonde.maisonchapback.controllers;


import com.tonde.maisonchapback.exceptions.CustomLogger;
import com.tonde.maisonchapback.services.RessourceLoader;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class RessourceLoaderController {

    private final RessourceLoader uploadPath;

    public RessourceLoaderController(RessourceLoader uploadPath) {
        this.uploadPath = uploadPath;
    }

    @GetMapping("down/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request, @PathVariable String filename) {
        CustomLogger.log("INFO", "File requested " + filename);
        Resource resource = uploadPath.loadFileAsResource(filename);
        CustomLogger.log("INFO", "File found " + filename);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            CustomLogger.log("ERROR", "Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
