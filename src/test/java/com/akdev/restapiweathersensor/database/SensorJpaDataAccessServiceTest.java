package com.akdev.restapiweathersensor.database;

import com.akdev.restapiweathersensor.database.repository.SensorRepository;
import com.akdev.restapiweathersensor.model.Sensor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SensorJpaDataAccessServiceTest {

    @Mock
    private SensorRepository sensorRepository;

    @InjectMocks
    private SensorJpaDataAccessService underTest;

    @Test
    void canSaveSensor() {
        var sensorToSave = Sensor.builder()
                .name("Test sensor")
                .build();

        underTest.save(sensorToSave);

        verify(sensorRepository, times(1)).save(sensorToSave);
    }

    @Test
    void successfulSave() {
        var sensorToSave = Sensor.builder()
                .name("Test sensor")
                .build();

        underTest.save(sensorToSave);

        var sensorArgumentCaptor
                = ArgumentCaptor.forClass(Sensor.class);

        verify(sensorRepository)
                .save(sensorArgumentCaptor.capture());

        Sensor capturedSensor = sensorArgumentCaptor.getValue();

        assertThat(capturedSensor).isEqualTo(sensorToSave);
    }

    @Test
    void canFindSensorByName() {
        final String name = "Test sensor";

        underTest.findSensorByName(name);

        verify(sensorRepository, times(1)).findSensorByName(name);
    }

    @Test
    void findByNameSuccess() {
        final String nameToFind = "Test sensor";
        var expectedSensor = Sensor.builder()
                .name("Test sensor")
                .build();

        when(sensorRepository.findSensorByName(nameToFind))
                .thenReturn(Optional.of(expectedSensor));
        var actualResult = underTest.findSensorByName(nameToFind);

        assertThat(actualResult.get()).isEqualTo(expectedSensor);
    }

    @Test
    void findByNameFail() {
        final String nameToFind = "Incorrect sensor";
        var expectedSensor = Sensor.builder()
                .name("Test sensor")
                .build();

        when(sensorRepository.findSensorByName(nameToFind))
                .thenReturn(Optional.empty());
        var actualResult = underTest.findSensorByName(nameToFind);

        assertThat(actualResult).isEmpty();
    }
}