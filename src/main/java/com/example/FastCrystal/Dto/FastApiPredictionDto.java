package com.example.FastCrystal.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FastApiPredictionDto {

    @JsonProperty("id_buscado")
    private Integer searchedId;

    @JsonProperty("arquivo")
    private String filename;

    @JsonProperty("resultado")
    private FastApiResultDto result;

    public Integer getSearchedId() {
        return searchedId;
    }

    public void setSearchedId(Integer searchedId) {
        this.searchedId = searchedId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public FastApiResultDto getResult() {
        return result;
    }

    public void setResult(FastApiResultDto result) {
        this.result = result;
    }
}