package com.akdev.restapiweathersensor.database;

import com.akdev.restapiweathersensor.database.repository.MeasurementRepository;
import com.akdev.restapiweathersensor.model.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MeasurementJpaDataAccessService implements MeasurementDao {

    private MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementJpaDataAccessService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Override
    public Measurement save(Measurement measurement) {
        return measurementRepository.save(measurement);
    }

    @Override
    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Override
    public Long rainyDaysCount() {
        return measurementRepository.countByIsRainingTrue();
    }

}
