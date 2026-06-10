package com.example.FastCrystal.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FastApiResultDto {

    @JsonProperty("classificacao")
    private String classification;

    @JsonProperty("confianca_percentual")
    private Double confidencePercentage;

    @JsonProperty("probabilidades_brutas")
    private List<Double> rawProbabilities;

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Double getConfidencePercentage() {
        return confidencePercentage;
    }

    public void setConfidencePercentage(Double confidencePercentage) {
        this.confidencePercentage = confidencePercentage;
    }

    public List<Double> getRawProbabilities() {
        return rawProbabilities;
    }

    public void setRawProbabilities(List<Double> rawProbabilities) {
        this.rawProbabilities = rawProbabilities;
    }
}