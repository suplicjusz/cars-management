package car_service;

import extensions.CarServiceParameterResolver;
import lombok.RequiredArgsConstructor;
import model.car.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import support.CarServiceTestingContent;

import java.util.List;

@RequiredArgsConstructor
@ExtendWith(CarServiceParameterResolver.class)
public class CarServiceGetMostExpensiveCarsTest implements CarServiceTestingContent<List<Car>>{
    private final CarServiceImpl carsService;

    @Override
    public List<Car> getContent() {
        return List.of(
                carsTestingContent.get(2)
        );
    }

    @Test
    void shouldReturnMostExpensiveCarsList() {

        Assertions
                .assertThat(carsService.getMostExpensiveCars())
                .containsAll(getContent());
    }
}
