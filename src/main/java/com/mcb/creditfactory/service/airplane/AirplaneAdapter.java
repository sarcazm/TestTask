package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AirplaneAdapter implements CollateralObject {

    private AirplaneDto airplaneDto;

    @Override
    public List getValues() {
        return airplaneDto.getValues();
    }

    @Override
    public Short getYear() {
        return airplaneDto.getYear();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.AIRPLANE;
    }
}
