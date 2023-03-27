package com.akdev.restapiweathersensor.mapper;

import com.akdev.restapiweathersensor.dto.MeasurementDto;
import com.akdev.restapiweathersensor.model.Measurement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeasurementMapperImpl implements MeasurementMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Measurement mapToMeasurement(MeasurementDto measurementDto) {
        return modelMapper.map(measurementDto, Measurement.class);
    }

    @Override
    public MeasurementDto mapToDto(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDto.class);
    }

}
