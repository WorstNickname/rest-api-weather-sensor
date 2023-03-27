package com.akdev.restapiweathersensor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class SensorDto {

    @NotEmpty(message = "Sensor name must not be empty!")
    @Size(min = 3, max = 60, message = "Sensor name must have from 3 to 60 characters!")
    private String name;

}
