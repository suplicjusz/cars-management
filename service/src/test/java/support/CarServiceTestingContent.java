package support;

import model.car.Car;
import model.enums.Color;

import java.math.BigDecimal;
import java.util.List;


public interface CarServiceTestingContent<T> {
    T getContent();

    List<Car> carsTestingContent = List.of(
            Car
                    .builder()
                    .id(0)
                    .model("AUDI")
                    .price(new BigDecimal("25500"))
                    .color(Color.BLACK)
                    .mileage(20500)
                    .components(List.of("AIR CONDITIONING", "ABS"))
                    .build(),
            Car
                    .builder()
                    .id(1)
                    .model("AUDI")
                    .price(new BigDecimal("52000"))
                    .color(Color.BLUE)
                    .mileage(115000)
                    .components(List.of("AIR CONDITIONING", "ABS", "LEATHER SEATS", "GPS"))
                    .build(),
            Car
                    .builder()
                    .id(2)
                    .model("BMW")
                    .price(new BigDecimal("105000"))
                    .color(Color.WHITE)
                    .mileage(305000)
                    .components(List.of("AIR CONDITIONING", "LEATHER SEATS"))
                    .build()
    );

    List<Car> carsWithSortedComponents = List.of(
            Car
                    .builder()
                    .id(0)
                    .model("AUDI")
                    .price(new BigDecimal("25500"))
                    .color(Color.BLACK)
                    .mileage(20500)
                    .components(List.of("ABS", "AIR CONDITIONING"))
                    .build(),
            Car
                    .builder()
                    .id(1)
                    .model("AUDI")
                    .price(new BigDecimal("52000"))
                    .color(Color.BLUE)
                    .mileage(115000)
                    .components(List.of("ABS", "AIR CONDITIONING", "GPS", "LEATHER SEATS"))
                    .build(),
            Car
                    .builder()
                    .id(2)
                    .model("BMW")
                    .price(new BigDecimal("105000"))
                    .color(Color.WHITE)
                    .mileage(305000)
                    .components(List.of("AIR CONDITIONING", "LEATHER SEATS"))
                    .build()

    );

}
