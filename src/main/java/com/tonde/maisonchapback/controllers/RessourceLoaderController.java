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

    private final RessourceLoader loader;

    public RessourceLoaderController(RessourceLoader loader) {
        this.loader = loader;
    }

    @GetMapping("down/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request, @PathVariable String filename) {
        CustomLogger.log("INFO", "File requested " + filename);
        Resource resource = loader.loadFileAsResource(filename);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            CustomLogger.log("INFO", "File founded " + contentType);
        } catch (IOException ex) {
            CustomLogger.log("ERROR", "Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
