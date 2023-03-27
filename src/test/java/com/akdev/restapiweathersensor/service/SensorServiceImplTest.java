package com.akdev.restapiweathersensor.service;

import com.akdev.restapiweathersensor.database.SensorDao;
import com.akdev.restapiweathersensor.model.Sensor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SensorServiceImplTest {

    @Mock
    private SensorDao sensorDao;

    @InjectMocks
    private SensorServiceImpl underTest;

    @Test
    void canFindSensorByName() {
        final String nameToFind = "Test sensor";
        var expectedSensor = Sensor.builder()
                .name(nameToFind)
                .build();

        when(sensorDao.findSensorByName(nameToFind))
                .thenReturn(Optional.of(expectedSensor));

        var actualResult = underTest.findSensorByName(nameToFind);
        assertThat(actualResult.get()).isEqualTo(expectedSensor);
    }

    @Test
    void canSaveSensor() {
        var sensorToSave = Sensor.builder()
                .name("Test sensor")
                .build();
        var sensorToReturn = Sensor.builder()
                .id(1337)
                .name("Test sensor")
                .measurements(Collections.emptyList())
                .build();

        when(sensorDao.save(sensorToSave))
                .thenReturn(sensorToReturn);

        assertThat(underTest.save(sensorToSave)).isEqualTo(sensorToReturn);
    }
}