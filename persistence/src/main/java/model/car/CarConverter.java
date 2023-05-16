package model.car;

import model.enums.Color;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public interface CarConverter {
    Function<Car, Color> toColor = car -> car.color;
    ToDoubleFunction<Car> toMileage = car -> car.mileage;
    Function<Car, String > toModel = car -> car.model;
    Function<Car, BigDecimal> toPrice = car -> car.price;

}
