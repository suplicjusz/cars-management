package car_service;

import extensions.CarServiceParameterResolver;
import lombok.RequiredArgsConstructor;
import model.car.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import support.CarServiceTestingContent;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@ExtendWith(CarServiceParameterResolver.class)
public class CarServiceGetCarsWithLowerMileageThenTest implements CarServiceTestingContent<List<Car>> {


    private final CarServiceImpl extensionService;

    @Override
    public List<Car> getContent() {
        return List.of(carsTestingContent.get(0));
    }

    @ParameterizedTest
    @DisplayName("when is limit of mileage lower zero")
    @MethodSource("invalidArgumentsSource")
    void shouldThrowsIllegalArgumentException(Double mileage) {
        Assertions
                .assertThatThrownBy(
                        () -> extensionService.getCarsWithLowerMileageThen(mileage))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Mileage cannot be a negative number!");
    }

    static Stream<Double> invalidArgumentsSource() {
        return Stream.of(-1.0, -5.0, -100.0, -2000.0, -3500.0);
    }

    @ParameterizedTest
    @DisplayName("when is limit of mileage correct and there are car in car collection")
    @MethodSource("correctArgumentsSource")
    void shouldReturnedListOfCarsTheyHaveMileageLowerThenLimit(Double mileage) {
        org.junit.jupiter.api.Assertions
                .assertEquals(
                        carsTestingContent,
                        extensionService.getCarsWithLowerMileageThen(mileage)
                );
    }

    static Stream<Double> correctArgumentsSource() {
        return Stream.of(305_001.0, 455_000.0, 500_000.0, 500_005.0, 501_000.0);
    }

    @ParameterizedTest
    @DisplayName("when is limit below all mileages of cars")
    @MethodSource("belowAllSource")
    void shouldReturnedEmptyList(Double mileage) {
        org.junit.jupiter.api.Assertions
                .assertEquals(
                        List.of(),
                        extensionService.getCarsWithLowerMileageThen(mileage)
                );
    }

    static Stream<Double> belowAllSource() {
        return Stream.of(1.0, 350.0, 500.0, 10_000.0, 20_499.0);
    }

}
