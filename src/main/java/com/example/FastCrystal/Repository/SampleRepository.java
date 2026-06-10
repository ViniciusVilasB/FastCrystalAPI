package com.example.FastCrystal.Repository;

import com.example.FastCrystal.Model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Integer> {
    List<Sample> findByOrderBySampleIdAsc();

    Optional<Sample> findTopBySampleIdOrderByCreatedAtDesc(Integer sampleId);
}