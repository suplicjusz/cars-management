package model.car;

import lombok.*;
import model.enums.Color;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
public class CarData {

    long id;
    String model;
    BigDecimal price;
    Color color;
    double mileage;
    List<String> components;

    public Car toCar() {
        return new Car(id, model, price, color, mileage, components);
    }


}
