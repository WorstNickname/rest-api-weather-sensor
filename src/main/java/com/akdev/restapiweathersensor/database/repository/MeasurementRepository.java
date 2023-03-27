package com.akdev.restapiweathersensor.database.repository;

import com.akdev.restapiweathersensor.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    Long countByIsRainingTrue();

}
