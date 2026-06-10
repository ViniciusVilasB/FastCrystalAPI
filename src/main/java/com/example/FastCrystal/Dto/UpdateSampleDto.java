package com.example.FastCrystal.Dto;

public class UpdateSampleDto {

    private String proteinName;

    private Double gravityLevel;

    private Double temperature;

    private Double mechanicalVibration;

    private String status;

    public UpdateSampleDto() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}