package com.akdev.restapiweathersensor.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MeasurementErrorResponse {

    private String message;
    private LocalDateTime timestamp;

}
