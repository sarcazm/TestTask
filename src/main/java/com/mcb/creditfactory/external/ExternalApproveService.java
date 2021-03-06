package com.mcb.creditfactory.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

@Service
@Slf4j
public class ExternalApproveService {
    private static final LocalDate MIN_ASSESS_DATE = LocalDate.of(2017, Month.OCTOBER, 1);
    private static final int MIN_CAR_YEAR = 2000;
    private static final BigDecimal MIN_CAR_VALUE = BigDecimal.valueOf(1000000);
    private static final int MIN_PLANE_YEAR = 1991;
    private static final BigDecimal MIN_PLANE_VALUE = BigDecimal.valueOf(230000000);


    public int approve(CollateralObject object) {
        for (CollateralValue collateralValue : object.getValues())
        {
            if (collateralValue.getDate() == null || collateralValue.getValue() == null)
                return -1;
        }
        if (object.getType() == null) {
            return -1;
        }

        int code;
        switch (object.getType()) {
            case CAR: code = approveCar(object); break;
            case AIRPLANE: code = approvePlane(object); break;
            default: code = -100;
        }

        return code;
    }

    private int approveCar(CollateralObject object) {
        CollateralValue collateralValue = object.getValues().get(0);
        if (object.getYear() < MIN_CAR_YEAR) {
            return -10;
        }
        if (collateralValue.getDate().isBefore(MIN_ASSESS_DATE)) {
            return -11;
        }
        if (collateralValue.getValue().compareTo(MIN_CAR_VALUE) < 0) {
            return -12;
        }

        return 0;
    }

    private int approvePlane(CollateralObject object) {
        for (CollateralValue collateralValue : object.getValues())
        {
            if (collateralValue.getDate().isBefore(MIN_ASSESS_DATE)) {
                return -21;
            }
            if (collateralValue.getValue().compareTo(MIN_PLANE_VALUE) < 0) {
                return -22;
            }
        }


        if (object.getYear() < MIN_PLANE_YEAR) {
            return -20;
        }
        return 0;
    }
}
