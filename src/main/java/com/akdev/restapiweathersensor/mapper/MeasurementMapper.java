package com.akdev.restapiweathersensor.mapper;

import com.akdev.restapiweathersensor.dto.MeasurementDto;
import com.akdev.restapiweathersensor.model.Measurement;

public interface MeasurementMapper {

    Measurement mapToMeasurement(MeasurementDto measurementDto);

    MeasurementDto mapToDto(Measurement measurement);
    
}
