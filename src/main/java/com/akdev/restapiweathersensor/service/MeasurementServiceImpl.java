package com.akdev.restapiweathersensor.service;

import com.akdev.restapiweathersensor.database.MeasurementDao;
import com.akdev.restapiweathersensor.model.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementDao measurementDao;
    private final SensorService sensorService;

    @Autowired
    public MeasurementServiceImpl(MeasurementDao measurementDao,
                                  SensorService sensorService) {
        this.measurementDao = measurementDao;
        this.sensorService = sensorService;
    }

    @Override
    public Measurement save(Measurement measurement) {
        var sensor = sensorService
                .findSensorByName(measurement.getSensor().getName());

        measurement.setSensor(sensor.get());
        measurement.setTime(LocalDateTime.now());
        return measurementDao.save(measurement);
    }

    @Override
    public List<Measurement> findAll() {
        return measurementDao.findAll();
    }

    @Override
    public Long rainyDaysCount() {
        return measurementDao.rainyDaysCount();
    }

}
