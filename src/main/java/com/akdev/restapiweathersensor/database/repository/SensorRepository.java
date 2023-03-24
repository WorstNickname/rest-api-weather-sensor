package com.akdev.restapiweathersensor.database.repository;

import com.akdev.restapiweathersensor.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    Optional<Sensor> findSensorByName(String name);

}
