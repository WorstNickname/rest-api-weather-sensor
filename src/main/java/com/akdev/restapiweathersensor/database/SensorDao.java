package com.akdev.restapiweathersensor.database;

import com.akdev.restapiweathersensor.model.Sensor;

import java.util.Optional;

public interface SensorDao {

    Sensor save(Sensor sensorToSave);

    Optional<Sensor> findSensorByName(String name);

}
