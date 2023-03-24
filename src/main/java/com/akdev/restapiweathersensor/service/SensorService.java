package com.akdev.restapiweathersensor.service;

import com.akdev.restapiweathersensor.model.Sensor;

import java.util.Optional;

public interface SensorService {

    Optional<Sensor> findSensorByName(String name);

    Sensor save(Sensor sensorToSave);
}
