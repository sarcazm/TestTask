package com.mcb.creditfactory.external;



import java.util.List;

public interface CollateralObject {
    List<CollateralValue> getValues();
    Short getYear();
    CollateralType getType();
}
