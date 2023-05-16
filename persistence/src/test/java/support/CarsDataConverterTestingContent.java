package support;

import model.car.CarData;
import model.enums.Color;

import java.math.BigDecimal;
import java.util.List;

public interface CarsDataConverterTestingContent {

    List<CarData> content = List.of(
            CarData
                    .builder()
                    .id(0)
                    .model("AUDI")
                    .price(new BigDecimal("20000"))
                    .color(Color.WHITE)
                    .mileage(5000)
                    .components(List.of("ABS"))
                    .build(),
            CarData
                    .builder()
                    .id(1)
                    .model("BMW")
                    .price(new BigDecimal("30000"))
                    .color(Color.WHITE)
                    .mileage(7000)
                    .components(List.of("ABS"))
                    .build()
    );
}
