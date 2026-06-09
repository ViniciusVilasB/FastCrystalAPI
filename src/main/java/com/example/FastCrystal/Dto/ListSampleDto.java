package com.example.FastCrystal.Dto;

import java.time.LocalDateTime;

public class ListSampleDto {

    private Integer sampleId;

    private String proteinName;

    private LocalDateTime captureDate;

    private Double temperature;

    private Double gravityLevel;

    private Double mechanicalVibration;

    private String status;

    private String imageUrl;

    private Integer expeditionEfficiencyScore;

    private String recommendedAction;

    // Prediction
    private String classification;

    private Double confidence;

    private LocalDateTime predictionDate;

    public ListSampleDto() {
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

    public LocalDateTime getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(LocalDateTime captureDate) {
        this.captureDate = captureDate;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getGravityLevel() {
        return gravityLevel;
    }

    public void setGravityLevel(Double gravityLevel) {
        this.gravityLevel = gravityLevel;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getExpeditionEfficiencyScore() {
        return expeditionEfficiencyScore;
    }

    public void setExpeditionEfficiencyScore(Integer expeditionEfficiencyScore) {
        this.expeditionEfficiencyScore = expeditionEfficiencyScore;
    }

    public String getRecommendedAction() {
        return recommendedAction;
    }

    public void setRecommendedAction(String recommendedAction) {
        this.recommendedAction = recommendedAction;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public LocalDateTime getPredictionDate() {
        return predictionDate;
    }

    public void setPredictionDate(LocalDateTime predictionDate) {
        this.predictionDate = predictionDate;
    }


}