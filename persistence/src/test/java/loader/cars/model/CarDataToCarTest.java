package loader.cars.model;

import extension.CarDataParameterResolver;
import lombok.RequiredArgsConstructor;
import model.car.Car;
import model.car.CarData;
import model.enums.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@ExtendWith(CarDataParameterResolver.class)
class CarDataToCarTest {
    private final CarData carData;

    static Car getContent() {
        return Car
                .builder()
                .id(0)
                .model("AUDI")
                .price(new BigDecimal("10000"))
                .color(Color.WHITE)
                .mileage(10000)
                .components(List.of("ABS"))
                .build();
    }

    @Test
    @DisplayName("When CarData object is calling the toCar method")
    void shouldConvertCarDataToCar() {
        Assertions
                .assertEquals(
                        carData.toCar(),
                        getContent()
                );
    }
}
