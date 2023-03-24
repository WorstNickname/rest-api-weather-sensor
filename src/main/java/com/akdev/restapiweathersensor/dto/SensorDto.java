package com.akdev.restapiweathersensor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SensorDto {

    @NotEmpty(message = "Sensor name must not be empty!")
    @Size(min = 3, max = 60, message = "Sensor name must have from 3 to 60 characters!")
    private String name;

}
