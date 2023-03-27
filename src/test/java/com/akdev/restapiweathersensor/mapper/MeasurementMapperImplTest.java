package com.akdev.restapiweathersensor.mapper;

import com.akdev.restapiweathersensor.dto.MeasurementDto;
import com.akdev.restapiweathersensor.dto.SensorDto;
import com.akdev.restapiweathersensor.model.Measurement;
import com.akdev.restapiweathersensor.model.Sensor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MeasurementMapperImplTest {

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private MeasurementMapperImpl underTest;

    @Test
    void mapToMeasurementTest() {
        var measurementDtoToMap = MeasurementDto.builder()
                .value(13.37)
                .isRaining(true)
                .sensor(SensorDto.builder()
                        .name("Test sensor")
                        .build())
                .build();
        var expectedMeasurement = Measurement.builder()
                .value(13.37)
                .isRaining(true)
                .sensor(Sensor.builder()
                        .name("Test sensor")
                        .measurements(Collections.emptyList())
                        .build())
                .build();

        var actualMeasurement = underTest.mapToMeasurement(measurementDtoToMap);

        assertThat(actualMeasurement).isEqualTo(expectedMeasurement);
    }

    @Test
    void mapToDtoTest() {
        var measurementToMap = Measurement.builder()
                .id(999L)
                .value(13.37)
                .isRaining(true)
                .time(LocalDateTime.now())
                .sensor(Sensor.builder()
                        .id(999)
                        .name("Test sensor")
                        .measurements(Collections.emptyList())
                        .build())
                .build();
        var expectedDto = MeasurementDto.builder()
                .value(13.37)
                .isRaining(true)
                .sensor(SensorDto.builder().name("Test sensor").build())
                .build();

        var actualDto = underTest.mapToDto(measurementToMap);

        assertThat(actualDto).isEqualTo(expectedDto);
    }

}