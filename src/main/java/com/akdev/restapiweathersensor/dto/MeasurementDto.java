package com.akdev.restapiweathersensor.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class MeasurementDto {

    @NotNull
    @Min(value = -100, message = "Must be more than -100")
    @Max(value = 100, message = "Must be less than 100")
    private Double value;

    @NotNull
    private Boolean isRaining;

    @NotNull
    private SensorDto sensor;

}
