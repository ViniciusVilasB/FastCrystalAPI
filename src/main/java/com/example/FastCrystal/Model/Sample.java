package com.example.FastCrystal.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "samples")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sample_id")
    private String sampleId;

    @Column(name = "protein_name")
    private String proteinName;

    @Column(name = "gravity_level")
    private Double gravityLevel;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "mechanical_vibration")
    private Double mechanicalVibration;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "capture_date")
    private java.time.LocalDateTime captureDate;

    @Column(name = "incubation_period")
    private Double incubationPeriod;

    @Column(name = "created_at", updatable = false)
    private java.time.LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = java.time.LocalDateTime.now();
        this.captureDate = java.time.LocalDateTime.now();

        if (this.incubationPeriod == null) {
            this.incubationPeriod = 0.0;
        }
    }

    private String status;

    public Sample() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSampleId() { return sampleId; }
    public void setSampleId(String sampleId) { this.sampleId = sampleId; }

    public String getProteinName() { return proteinName; }
    public void setProteinName(String proteinName) { this.proteinName = proteinName; }

    public Double getGravityLevel() { return gravityLevel; }
    public void setGravityLevel(Double gravityLevel) { this.gravityLevel = gravityLevel; }

    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }

    public Double getMechanicalVibration() { return mechanicalVibration; }
    public void setMechanicalVibration(Double mechanicalVibration) { this.mechanicalVibration = mechanicalVibration; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public java.time.LocalDateTime getCaptureDate() { return captureDate; }
    public void setCaptureDate(java.time.LocalDateTime captureDate) { this.captureDate = captureDate; }

    public Double getIncubationPeriod() { return incubationPeriod; }
    public void setIncubationPeriod(Double incubationPeriod) { this.incubationPeriod = incubationPeriod; }

    public java.time.LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(java.time.LocalDateTime createdAt) { this.createdAt = createdAt; }
}