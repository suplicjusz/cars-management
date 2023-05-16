package car_service;

import extensions.CarServiceParameterResolver;
import lombok.RequiredArgsConstructor;
import model.car.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import support.CarServiceTestingContent;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@ExtendWith(CarServiceParameterResolver.class)
public class CarServiceGetCarsWithPriceInCompartmentTest implements CarServiceTestingContent<Map<Long, List<Car>>> {

    private final CarServiceImpl extensionService;

    @Override
    public Map<Long, List<Car>> getContent() {
        return Map.of(
                1L, List.of(carsTestingContent.get(1)),
                2L, List.of(carsTestingContent.get(1), carsTestingContent.get(2))
        );
    }

    @Test
    @DisplayName("when given compartment is invalid")
    void shouldThrowsIllegalArgumentException() {
        Assertions
                .assertThatThrownBy(
                        () -> extensionService.getCarsWithPriceInCompartment(new BigDecimal("20000"), new BigDecimal("100"))
                )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("MIN value cannot be major then MAX value!");
    }

    @Test
    @DisplayName("when arguments are correct v.1")
    void shouldReturnedListWithOneArgument() {
        Assertions
                .assertThat(extensionService.getCarsWithPriceInCompartment(new BigDecimal("50000"), new BigDecimal("60000")))
                .containsAll(getContent().get(1L));

    }

    @Test
    @DisplayName("when arguments are correct v.2")
    void shouldReturnedListWithTwoArguments() {
        Assertions
                .assertThat(extensionService.getCarsWithPriceInCompartment(new BigDecimal("50000"), new BigDecimal("120000")))
                .containsAll(getContent().get(2L));
    }

    @Test
    @DisplayName("when lower border of compartment is under all given prices of cars")
    void shouldReturnedEmptyList() {
        Assertions
                .assertThat(extensionService.getCarsWithPriceInCompartment(new BigDecimal("120000"), new BigDecimal("130000")))
                .isEmpty();
    }

}
