package com.akdev.restapiweathersensor.database;

import com.akdev.restapiweathersensor.database.repository.SensorRepository;
import com.akdev.restapiweathersensor.model.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SensorJpaDataAccessService implements SensorDao {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorJpaDataAccessService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Sensor save(Sensor sensorToSave) {
        return sensorRepository.save(sensorToSave);
    }

    @Override
    public Optional<Sensor> findSensorByName(String name) {
        return sensorRepository.findSensorByName(name);
    }

}
