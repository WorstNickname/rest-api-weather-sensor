package com.akdev.restapiweathersensor.controller;

import com.akdev.restapiweathersensor.dto.SensorDto;
import com.akdev.restapiweathersensor.mapper.SensorMapper;
import com.akdev.restapiweathersensor.service.SensorService;
import com.akdev.restapiweathersensor.validator.SensorValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.akdev.restapiweathersensor.exception.ExceptionUtil.returnExceptionMessage;

@RestController
@RequestMapping("/api/v1/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final SensorMapper sensorMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService,
                            SensorMapper sensorMapper,
                            SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorMapper = sensorMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addNewSensor(@RequestBody @Valid SensorDto sensorDto,
                                                   BindingResult bindingResult) {
        var sensor = sensorMapper.mapToSensor(sensorDto);

        sensorValidator.validate(sensor, bindingResult);

        if (bindingResult.hasErrors()) {
            returnExceptionMessage(bindingResult);
        }

        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
