package com.akdev.restapiweathersensor.service;

import com.akdev.restapiweathersensor.database.SensorDao;
import com.akdev.restapiweathersensor.model.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorServiceImpl implements SensorService {

    private final SensorDao sensorDao;

    @Autowired
    public SensorServiceImpl(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    @Override
    public Optional<Sensor> findSensorByName(String name) {
        return sensorDao.findSensorByName(name);
    }

    @Override
    @Transactional
    public Sensor save(Sensor sensorToSave) {
        return sensorDao.save(sensorToSave);
    }

}
