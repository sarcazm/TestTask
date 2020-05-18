package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CarAdapter implements CollateralObject {
    private CarDto car;

    @Override
    public List getValues() {
        return car.getValues();
    }

    @Override
    public Short getYear() {
        return car.getYear();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.CAR;
    }
}
