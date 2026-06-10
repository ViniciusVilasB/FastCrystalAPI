package com.example.FastCrystal.Service;

import com.example.FastCrystal.Dto.FastApiPredictionDto;
import com.example.FastCrystal.Dto.ListSampleDto;
import com.example.FastCrystal.Dto.UpdateSampleDto;
import com.example.FastCrystal.Model.Prediction;
import com.example.FastCrystal.Model.Sample;
import com.example.FastCrystal.Repository.PredictionRepository;
import com.example.FastCrystal.Repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
    private SampleRepository sampleRepository;

    @Autowired
    private PredictionRepository predictionRepository;

    private static final String UPLOAD_DIR = "uploads";

    // RestTemplate is used to make HTTP requests to the Python API
    private final RestTemplate restTemplate = new RestTemplate();

    public Sample createSample(
            Integer sampleId,
            String proteinName,
            Double gravityLevel,
            Double temperature,
            Double mechanicalVibration,
            LocalDateTime captureDate,
            MultipartFile image

    ) throws IOException {
        Files.createDirectories(Paths.get(UPLOAD_DIR));
        String originalName = Objects.requireNonNull(image.getOriginalFilename());
        String extension = originalName.substring(originalName.lastIndexOf("."));
        String fileName = UUID.randomUUID() + extension;
        Path destination = Paths.get(UPLOAD_DIR, fileName);
        Files.copy(image.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        Sample sample = new Sample();

        sample.setSampleId(sampleId);
        sample.setProteinName(proteinName);
        sample.setGravityLevel(gravityLevel);
        sample.setTemperature(temperature);
        sample.setMechanicalVibration(mechanicalVibration);
        sample.setCaptureDate(captureDate);
        sample.setImageFilename(fileName);
        sample.setStatus("PENDING");

        // 1. Save the sample FIRST so we have it persisted
        Sample savedSample = sampleRepository.save(sample);

        // 2. Call AI and save prediction
        try {
            requestAiPredictionAndSave(savedSample);
            // If success, update status
            savedSample.setStatus("ANALYZED");
            savedSample = sampleRepository.save(savedSample);
        } catch (Exception e) {
            System.err.println("Failed to fetch prediction from AI: " + e.getMessage());
        }

        return savedSample;
    }

    private void requestAiPredictionAndSave(Sample sample) {
        // Calling Python API using the sampleId parameter
        String pythonApiUrl = "http://localhost:8000/predict/" + sample.getSampleId();

        // Perform GET request and map JSON to DTO
        FastApiPredictionDto response = restTemplate.getForObject(pythonApiUrl, FastApiPredictionDto.class);

        if (response != null && response.getResult() != null) {
            Prediction prediction = new Prediction();

            // Linking the prediction to the database internal ID
            prediction.setSampleId(sample.getId());
            prediction.setClassification(response.getResult().getClassification());
            prediction.setConfidence(response.getResult().getConfidencePercentage());
            prediction.setPredictionDate(LocalDateTime.now());

            predictionRepository.save(prediction);
        }
    }

    public List<Integer> getAvailableSamples() {

        return sampleRepository
                .findAll()
                .stream()
                .map(Sample::getSampleId)
                .distinct()
                .sorted()
                .toList();
    }

    public List<ListSampleDto> getAllSamples() {
        List<Sample> samples = sampleRepository.findAll();

        return samples.stream().map(sample -> {
            ListSampleDto dto = new ListSampleDto();

            dto.setSampleId(sample.getSampleId());
            dto.setProteinName(sample.getProteinName());
            dto.setCaptureDate(sample.getCaptureDate());
            dto.setTemperature(sample.getTemperature());
            dto.setGravityLevel(sample.getGravityLevel());
            dto.setMechanicalVibration(sample.getMechanicalVibration());
            dto.setStatus(sample.getStatus());
            dto.setImageUrl("http://localhost:8080/images/" + sample.getImageFilename());
            int score = calculateEfficiency(sample);
            dto.setExpeditionEfficiencyScore(score);
            dto.setRecommendedAction(determineAction(score));

            Prediction prediction = predictionRepository.findTopBySampleIdOrderByPredictionDateDesc(sample.getId())
                    .orElse(null);

            if(prediction != null) {
                dto.setClassification(prediction.getClassification());
                dto.setConfidence(prediction.getConfidence());
                dto.setPredictionDate(prediction.getPredictionDate());
            }

            return dto;
        })
        .toList();
    }

    public ListSampleDto getLatestSample(Integer sampleId) {

        Sample sample = sampleRepository.findTopBySampleIdOrderByCreatedAtDesc(sampleId).orElseThrow();

        ListSampleDto dto = new ListSampleDto();

        dto.setSampleId(sample.getSampleId());
        dto.setProteinName(sample.getProteinName());
        dto.setCaptureDate(sample.getCaptureDate());
        dto.setTemperature(sample.getTemperature());
        dto.setGravityLevel(sample.getGravityLevel());
        dto.setMechanicalVibration(sample.getMechanicalVibration());
        dto.setStatus(sample.getStatus());
        dto.setImageUrl("http://localhost:8080/api/images/" + sample.getImageFilename());
        int score = calculateEfficiency(sample);
        dto.setExpeditionEfficiencyScore(score);
        dto.setRecommendedAction(determineAction(score));

        Prediction prediction = predictionRepository.findTopBySampleIdOrderByPredictionDateDesc(sample.getId())
                .orElse(null);

        if(prediction != null) {
            dto.setClassification(prediction.getClassification());
            dto.setConfidence(prediction.getConfidence());
            dto.setPredictionDate(prediction.getPredictionDate());
        }

        return dto;
    }

    public Sample updateSample(Integer id, UpdateSampleDto dto) {

        Sample sample = sampleRepository.findById(id).orElseThrow(() -> new RuntimeException("Sample not found"));

        sample.setProteinName(dto.getProteinName());
        sample.setGravityLevel(dto.getGravityLevel());
        sample.setTemperature(dto.getTemperature());
        sample.setMechanicalVibration(dto.getMechanicalVibration());
        sample.setStatus(dto.getStatus());

        return sampleRepository.save(sample);
    }

    public void deleteSample(Integer id) {

        Sample sample = sampleRepository.findById(id).orElseThrow(() -> new RuntimeException("Sample not found"));

        if (sample.getImageFilename() != null) {
            Path imagePath = Paths.get("uploads").resolve(sample.getImageFilename());
            try {
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                System.out.println("Failed to delete image: " + e.getMessage());
            }
        }

        sampleRepository.delete(sample);
    }

    // Helper functions
    private int calculateEfficiency(Sample sample) {
        double score = 100 - (sample.getMechanicalVibration() * 10) - (Math.abs(sample.getTemperature() - 22) * 3);

        return Math.max(0, Math.min(100, (int) score));
    }

    private String determineAction(int score) {
        if(score >= 80)
            return "Continue incubation";

        if(score >= 50)
            return "Monitor closely";

        return "Reevaluate experiment";
    }
}