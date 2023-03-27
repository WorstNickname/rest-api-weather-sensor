package com.akdev.restapiweathersensor.validator;

import com.akdev.restapiweathersensor.model.Measurement;
import com.akdev.restapiweathersensor.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class MeasurementValidatorImpl implements MeasurementValidator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidatorImpl(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isInstance(MeasurementValidator.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var measurement = (Measurement) target;

        if (sensorService.findSensorByName(measurement.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "No such sensor");
        }
    }

}
