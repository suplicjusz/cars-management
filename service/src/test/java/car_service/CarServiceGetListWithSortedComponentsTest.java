package car_service;

import extensions.CarServiceParameterResolver;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import support.CarServiceTestingContent;

@RequiredArgsConstructor
@ExtendWith(CarServiceParameterResolver.class)
public class CarServiceGetListWithSortedComponentsTest {

    private final CarServiceImpl carsService;

    @Test
    void shouldReturnedCarsWithSortedComponents() {
        Assertions
                .assertThat(carsService.getListOfCarsWithSortedComponents())
                .containsAll(CarServiceTestingContent.carsWithSortedComponents);
    }
}
