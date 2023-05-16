package model.car;

import lombok.*;
import model.enums.Color;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Car {
    long id;
    String model;
    BigDecimal price;
    Color color;
    double mileage;
    List<String> components;


    public boolean hasMileageLowerThan(double mileage) {
        return this.mileage < mileage;
    }

    public List<String> getComponents() {
        return components;
    }

    public Car getCarWithSortedComponents() {
        List<String> newComponents = new ArrayList<>(components);
        Collections.sort(newComponents);
        return new Car(id, model, price, color, mileage, newComponents);
    }


}