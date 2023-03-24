package com.akdev.restapiweathersensor.controller;

import com.akdev.restapiweathersensor.dto.SensorDto;
import com.akdev.restapiweathersensor.exception.MeasurementErrorResponse;
import com.akdev.restapiweathersensor.exception.MeasurementException;
import com.akdev.restapiweathersensor.mapper.SensorMapper;
import com.akdev.restapiweathersensor.service.SensorService;
import com.akdev.restapiweathersensor.validator.SensorValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.akdev.restapiweathersensor.exception.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/api/sensors")
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

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> addNewSensor(@RequestBody @Valid SensorDto sensorDto,
                                                   BindingResult bindingResult) {

        var sensor = sensorMapper.mapToSensor(sensorDto);

        sensorValidator.validate(sensor, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }

        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // TODO: 24.03.2023 make global exception handling
    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        var response = new MeasurementErrorResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
