package com.akdev.restapiweathersensor.mapper;

import com.akdev.restapiweathersensor.dto.SensorDto;
import com.akdev.restapiweathersensor.model.Sensor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SensorMapperImplTest {

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private SensorMapperImpl underTest;

    @Test
    void mapToDtoTest() {
        var sensorToMap = Sensor.builder()
                .id(999)
                .name("Test sensor")
                .measurements(Collections.emptyList())
                .build();
        var expectedDto = SensorDto.builder().name("Test sensor").build();

        var actualDto = underTest.mapToDto(sensorToMap);

        assertThat(actualDto).isEqualTo(expectedDto);
    }

    @Test
    void mapToSensorTest() {
        var sensorDtoToMap = SensorDto.builder().name("Test sensor").build();
        var expectedSensor = Sensor.builder()
                .name("Test sensor")
                .measurements(Collections.emptyList())
                .build();

        var actualSensor = underTest.mapToSensor(sensorDtoToMap);

        assertThat(actualSensor).isEqualTo(expectedSensor);
    }

}