package com.example.FastCrystal.Service;

import com.example.FastCrystal.Model.Sample;
import com.example.FastCrystal.Repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class SampleService {

    @Autowired
    private SampleRepository repository;

    private static final String UPLOAD_DIR =
            "uploads";

    public Sample createSample(

            Integer sampleId,

            String proteinName,

            Double gravityLevel,

            Double temperature,

            Double mechanicalVibration,

            LocalDateTime captureDate,

            MultipartFile image

    ) throws IOException {

        Files.createDirectories(
                Paths.get(UPLOAD_DIR)
        );

        String originalName =
                Objects.requireNonNull(
                        image.getOriginalFilename()
                );

        String extension =
                originalName.substring(
                        originalName.lastIndexOf(".")
                );

        String fileName =
                UUID.randomUUID()
                        + extension;

        Path destination =
                Paths.get(
                        UPLOAD_DIR,
                        fileName
                );

        Files.copy(
                image.getInputStream(),
                destination,
                StandardCopyOption.REPLACE_EXISTING
        );

        Sample sample =
                new Sample();

        sample.setSampleId(sampleId);

        sample.setProteinName(proteinName);

        sample.setGravityLevel(gravityLevel);

        sample.setTemperature(temperature);

        sample.setMechanicalVibration(
                mechanicalVibration
        );

        sample.setCaptureDate(
                captureDate
        );

        sample.setImageFilename(
                fileName
        );

        sample.setStatus(
                "PENDING"
        );

        return repository.save(sample);
    }

    public List<Sample> getAllSamples() {
        return repository.findAll();
    }
}