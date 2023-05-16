package support;

import model.car.Car;
import model.enums.Color;

import java.math.BigDecimal;
import java.util.List;

public interface CarTestingContent {

    Car content = Car
            .builder()
            .id(0)
            .model("AUDI")
            .price(new BigDecimal("10000"))
            .color(Color.WHITE)
            .mileage(10000)
            .components(List.of("ABS", "AIR CONDITIONING", "GPS", "LEATHER SEATS"))
            .build();
}
