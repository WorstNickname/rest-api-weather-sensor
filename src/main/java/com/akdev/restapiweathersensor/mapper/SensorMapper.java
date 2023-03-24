package com.akdev.restapiweathersensor.mapper;

import com.akdev.restapiweathersensor.dto.SensorDto;
import com.akdev.restapiweathersensor.model.Sensor;

public interface SensorMapper {

    Sensor mapToSensor(SensorDto sensorDto);

    SensorDto mapToDto(Sensor sensor);

}
