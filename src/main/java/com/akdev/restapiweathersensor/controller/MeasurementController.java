package com.akdev.restapiweathersensor.controller;

import com.akdev.restapiweathersensor.dto.MeasurementDto;
import com.akdev.restapiweathersensor.mapper.MeasurementMapper;
import com.akdev.restapiweathersensor.service.MeasurementService;
import com.akdev.restapiweathersensor.validator.MeasurementValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.akdev.restapiweathersensor.exception.ExceptionUtil.returnExceptionMessage;

@RestController
@RequestMapping("/api/v1/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final MeasurementMapper measurementMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService,
                                 MeasurementValidator measurementValidator,
                                 MeasurementMapper measurementMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.measurementMapper = measurementMapper;
    }

    @GetMapping
    public ResponseEntity<List<MeasurementDto>> getMeasurements() {
        var measurements = measurementService.findAll()
                .stream()
                .map(measurementMapper::mapToDto)
                .toList();
        return new ResponseEntity<>(measurements, HttpStatus.OK);
    }

    @GetMapping("/rainy-days-count")
    public ResponseEntity<Long> getRainyDaysCount() {
        var count = measurementService.rainyDaysCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDto measurementDto,
                                                     BindingResult bindingResult) {
        var measurement = measurementMapper.mapToMeasurement(measurementDto);

        measurementValidator.validate(measurement, bindingResult);

        if (bindingResult.hasErrors()) {
            returnExceptionMessage(bindingResult);
        }

        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
