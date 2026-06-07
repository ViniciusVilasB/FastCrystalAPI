package com.example.FastCrystal.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "samples")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sample_id")
    private Integer sampleId;

    @Column(name = "protein_name")
    private String proteinName;

    @Column(name = "gravity_level")
    private Double gravityLevel;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "mechanical_vibration")
    private Double mechanicalVibration;

    @Column(name = "image_path")
    private String imageFilename;

    @Column(name = "capture_date")
    private LocalDateTime captureDate;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {

        this.createdAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = "PENDING";
        }
    }

    public Sample() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSampleId() {
        return sampleId;
    }

    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }

    public String getProteinName() {
        return proteinName;
    }

    public void setProteinName(String proteinName) {
        this.proteinName = proteinName;
    }

    public Double getGravityLevel() {
        return gravityLevel;
    }

    public void setGravityLevel(Double gravityLevel) {
        this.gravityLevel = gravityLevel;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getMechanicalVibration() {
        return mechanicalVibration;
    }

    public void setMechanicalVibration(Double mechanicalVibration) {
        this.mechanicalVibration = mechanicalVibration;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    public LocalDateTime getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(LocalDateTime captureDate) {
        this.captureDate = captureDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}