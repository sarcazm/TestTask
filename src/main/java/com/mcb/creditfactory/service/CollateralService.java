package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO: reimplement this
@Service
public class CollateralService {
    @Autowired
    private CarService carService;
   @Autowired
    private AirplaneService airplaneService;

    @SuppressWarnings("ConstantConditions")
    public Long saveCollateral(Collateral object)  {
        //тут по хорошему, прежде чем сохранять, надо бы поискать. имеется ли данный объект уже у нас в базе
        //плюс надо знать, как мы будем идентифицировать наш объект обеспечения
        checkNull(object);
        boolean approved;
        CarDto carDto = null;
        AirplaneDto airplaneDto = null;
        if (object instanceof CarDto) {
            carDto = (CarDto) object;
            approved = carService.approve(carDto);
            if (!approved) {
                return null;
            }
            return Optional.of(carDto)
                    .map(carService::fromDto)
                    .map(carService::save)
                    .map(carService::getId)
                    .orElse(null);
        }else if (object instanceof AirplaneDto) {
            airplaneDto = (AirplaneDto) object;
            approved = airplaneService.approve(airplaneDto);
            if (!approved) {
                return null;
            }
            return Optional.of(airplaneDto)
                    .map(airplaneService::fromDto)
                    .map(airplaneService::save)
                    .map(airplaneService::getId)
                    .orElse(null);
        }else return null;

        //их код
        /*if (!(object instanceof CarDto)) {
            throw new IllegalArgumentException();
        }

        CarDto car = (CarDto) object;
        boolean approved = carService.approve(car);
        if (!approved) {
            return null;
        }

        return Optional.of(car)
                .map(carService::fromDto)
                .map(carService::save)
                .map(carService::getId)
                .orElse(null);

         */

    }

    public Collateral getInfo(Collateral object) {
        checkNull(object);

        if (object instanceof CarDto) {

            return Optional.of((CarDto) object)
                    .map(carService::fromDto)
                    .map(carService::getId)
                    .flatMap(carService::load)
                    .map(carService::toDTO)
                    .orElse(null);
        }else if (object instanceof AirplaneDto) {
            return Optional.of((AirplaneDto) object)
                    .map(airplaneService::fromDto)
                    .map(airplaneService::getId)
                    .flatMap(airplaneService::load)
                    .map(airplaneService::toDTO)
                    .orElse(null);
        }else return null;

    }

    private void checkNull(Collateral object){
        if (!(object instanceof CarDto || object instanceof AirplaneDto)) {
            throw new IllegalArgumentException();
        }
    }

}
