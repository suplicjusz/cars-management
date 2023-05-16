package car_service;

import extensions.CarServiceParameterResolver;
import lombok.RequiredArgsConstructor;
import model.car.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import support.CarServiceTestingContent;

import java.util.Map;

@RequiredArgsConstructor
@ExtendWith(CarServiceParameterResolver.class)
public class CarServiceGetMostExpensiveCarByModelTest implements CarServiceTestingContent<Map<String, Car>> {

    private final CarServiceImpl carsService;

    @Override
    public Map<String, Car> getContent() {
        return Map.of(
                "AUDI", carsTestingContent.get(1),
                "BMW", carsTestingContent.get(2)
        );
    }

    @Test
    void shouldReturnMostExpensiveCarByModelMap() {
        Assertions
                .assertThat(carsService.getMostExpensiveCarByModel())
                .containsAllEntriesOf(getContent());
    }
}
