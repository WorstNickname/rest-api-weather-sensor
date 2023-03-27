package com.akdev.restapiweathersensor.database;

import com.akdev.restapiweathersensor.model.Measurement;

import java.util.List;

public interface MeasurementDao {

    Measurement save(Measurement measurement);

    List<Measurement> findAll();

    Long rainyDaysCount();

}
