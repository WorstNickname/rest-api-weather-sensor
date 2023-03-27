package com.akdev.restapiweathersensor.validator;

import com.akdev.restapiweathersensor.model.Measurement;
import com.akdev.restapiweathersensor.model.Sensor;
import com.akdev.restapiweathersensor.service.SensorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SensorValidatorImplTest {

    @Mock
    private SensorServiceImpl sensorService;

    @InjectMocks
    private SensorValidatorImpl underTest;

    @Test
    void validate_success() {
        var validSensor = Sensor.builder()
                .name("Test sensor")
                .build();
        Errors errors = new BeanPropertyBindingResult(validSensor, "validSensor");
        when(sensorService.findSensorByName(validSensor.getName()))
                .thenReturn(Optional.empty());

        underTest.validate(validSensor, errors);

        assertFalse(errors.hasErrors());
    }

    @Test
    void validate_fail() {
        var notValidSensor = Sensor.builder()
                .name("Test sensor")
                .build();
        Errors errors = new BeanPropertyBindingResult(notValidSensor, "notValidSensor");
        when(sensorService.findSensorByName(notValidSensor.getName()))
                .thenReturn(Optional.of(notValidSensor));

        underTest.validate(notValidSensor, errors);

        assertTrue(errors.hasErrors());
    }

    @Test
    void should_support_Sensor_class() {
        var result = underTest.supports(Sensor.class);

        assertThat(result).isTrue();
    }

    @Test
    void should_not_support_other_class() {
        List<Boolean> results = List.of(
                underTest.supports(Measurement.class),
                underTest.supports(Object.class),
                underTest.supports(Integer.class)
        );

        assertThat(results).allMatch(it -> it.equals(false));
    }

}