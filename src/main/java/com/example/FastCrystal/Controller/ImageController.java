package com.example.FastCrystal.Controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/images")
public class ImageController {

    private static final String UPLOAD_DIR =
            "uploads";

    @GetMapping("/{filename}")
    public ResponseEntity<Resource>
    getImage(
            @PathVariable String filename
    ) throws IOException {

        Path file =
                Paths.get(
                        UPLOAD_DIR,
                        filename
                );

        Resource resource =
                new UrlResource(
                        file.toUri()
                );

        return ResponseEntity.ok()
                .contentType(
                        MediaType.IMAGE_JPEG
                )
                .body(resource);
    }
}