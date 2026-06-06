package com.example.FastCrystal.Service;

import com.example.FastCrystal.Dto.SampleRequestDto;
import com.example.FastCrystal.Model.Sample;
import com.example.FastCrystal.Repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    @Autowired
    private SampleRepository repository;

    public Sample createSample(SampleRequestDto dto) {
        Sample sample = new Sample();

        sample.setSampleId(dto.getSampleId());
        sample.setProteinName(dto.getProteinName());
        sample.setGravityLevel(dto.getGravityLevel());
        sample.setTemperature(dto.getTemperature());
        sample.setMechanicalVibration(dto.getMechanicalVibration());
        sample.setImagePath(dto.getImagePath());

        return repository.save(sample);
    }
}