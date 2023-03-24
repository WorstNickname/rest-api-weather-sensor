package com.akdev.restapiweathersensor.validator;

import com.akdev.restapiweathersensor.model.Sensor;
import com.akdev.restapiweathersensor.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class SensorValidatorImpl implements SensorValidator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidatorImpl(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isInstance(Sensor.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var sensor = (Sensor) target;

        if (sensorService.findSensorByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "Sensor with this name already exists");
        }

    }

}
