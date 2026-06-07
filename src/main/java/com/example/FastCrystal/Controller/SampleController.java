package com.example.FastCrystal.Controller;

import com.example.FastCrystal.Dto.SampleRequestDto;
import com.example.FastCrystal.Model.Sample;
import com.example.FastCrystal.Service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/add-sample")
public class SampleController {

    @Autowired
    private SampleService service;

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Sample> addSample(

            @RequestParam Integer sampleId,

            @RequestParam String proteinName,

            @RequestParam Double gravityLevel,

            @RequestParam Double temperature,

            @RequestParam Double mechanicalVibration,

            @RequestParam LocalDateTime captureDate,

            @RequestParam MultipartFile image

    ) throws IOException {

        Sample sample =
                service.createSample(
                        sampleId,
                        proteinName,
                        gravityLevel,
                        temperature,
                        mechanicalVibration,
                        captureDate,
                        image
                );

        return ResponseEntity.ok(sample);
    }

    @GetMapping
    public ResponseEntity<?> getAllSamples() {
        return ResponseEntity.ok(
                service.getAllSamples()
        );
    }
}