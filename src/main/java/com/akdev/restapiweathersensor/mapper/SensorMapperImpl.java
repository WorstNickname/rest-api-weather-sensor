package com.akdev.restapiweathersensor.mapper;

import com.akdev.restapiweathersensor.dto.SensorDto;
import com.akdev.restapiweathersensor.model.Sensor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorMapperImpl implements SensorMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public SensorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SensorDto mapToDto(Sensor sensor) {
        return modelMapper.map(sensor, SensorDto.class);
    }

    @Override
    public Sensor mapToSensor(SensorDto sensorDto) {
        return modelMapper.map(sensorDto, Sensor.class);
    }

}
