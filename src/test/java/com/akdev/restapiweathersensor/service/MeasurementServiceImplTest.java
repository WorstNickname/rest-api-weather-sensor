package com.akdev.restapiweathersensor.service;

import com.akdev.restapiweathersensor.database.MeasurementJpaDataAccessService;
import com.akdev.restapiweathersensor.model.Measurement;
import com.akdev.restapiweathersensor.model.Sensor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeasurementServiceImplTest {

    @Mock
    private SensorServiceImpl sensorService;

    @Mock
    private MeasurementJpaDataAccessService dao;

    @InjectMocks
    private MeasurementServiceImpl underTest;

    @Test
    void canSaveMeasurement() {
        Measurement measurementToSave = Measurement.builder()
                .value(13.37)
                .isRaining(true)
                .time(LocalDateTime.now())
                .sensor(Sensor.builder()
                        .name("Test sensor")
                        .build())
                .build();

        when(sensorService.findSensorByName("Test sensor"))
                .thenReturn(Optional.of(
                                Sensor.builder()
                                        .name("Test sensor")
                                        .measurements(Collections.emptyList())
                                        .build()
                        )
                );

        underTest.save(measurementToSave);

        ArgumentCaptor<Measurement> argumentCaptor =
                ArgumentCaptor.forClass(Measurement.class);

        verify(dao).save(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue()).isEqualTo(measurementToSave);
    }

    @Test
    void canFindAll() {
        underTest.findAll();

        verify(dao).findAll();
    }

    @Test
    void rainyDaysCount() {
        underTest.rainyDaysCount();

        verify(dao).rainyDaysCount();
    }

}