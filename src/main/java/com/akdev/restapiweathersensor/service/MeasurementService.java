package com.akdev.restapiweathersensor.service;

import com.akdev.restapiweathersensor.model.Measurement;

import java.util.List;

public interface MeasurementService {

    Measurement save(Measurement measurement);

    List<Measurement> findAll();

    Long rainyDaysCount();

}
