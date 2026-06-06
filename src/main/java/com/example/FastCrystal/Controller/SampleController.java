package com.example.FastCrystal.Controller;

import com.example.FastCrystal.Dto.SampleRequestDto;
import com.example.FastCrystal.Model.Sample;
import com.example.FastCrystal.Service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add-sample")
public class SampleController {

    @Autowired
    private SampleService service;

    @PostMapping()
    public ResponseEntity<Sample> addSample(@RequestBody SampleRequestDto dto) {

        Sample savedSample = service.createSample(dto);

        return ResponseEntity.ok(savedSample);
    }
}