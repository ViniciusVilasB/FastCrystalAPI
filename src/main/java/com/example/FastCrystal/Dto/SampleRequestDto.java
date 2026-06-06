package com.example.FastCrystal.Dto;

public class SampleRequestDto {

    private String sampleId;
    private String proteinName;
    private Double gravityLevel;
    private Double temperature;
    private Double mechanicalVibration;
    private String imagePath;

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
}