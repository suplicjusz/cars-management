package model.car;

import java.util.Comparator;

public interface CarComparator {
    Comparator<Car> compareByColor = Comparator.comparing(car -> car.color.index);
    Comparator<Car> compareByMileage = Comparator.comparing(car -> car.mileage);
    Comparator<Car> compareByModel = Comparator.comparing(car -> car.model);
    Comparator<Car> compareByPrice = Comparator.comparing(car -> car.price);
}
