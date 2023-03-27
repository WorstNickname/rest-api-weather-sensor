package com.akdev.restapiweathersensor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Measurement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = -100, message = "Must be more than -100")
    @Max(value = 100, message = "Must be less than 100")
    private Double value;

    @Column(name = "is_raining")
    @NotNull
    private Boolean isRaining;

    @NotNull
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sensor_id")
    @NotNull
    private Sensor sensor;

}
