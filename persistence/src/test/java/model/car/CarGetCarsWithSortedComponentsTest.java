package model.car;

import extension.CarParameterResolver;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import support.CarTestingContent;

@RequiredArgsConstructor
@ExtendWith(CarParameterResolver.class)
public class CarGetCarsWithSortedComponentsTest implements CarTestingContent {

    private final Car car;

    @Test
    @DisplayName("when car object is calling the getCarWithSortedComponents method")
    void shouldReturnCarWithSortedComponents() {
        Assertions
                .assertEquals(
                        content,
                        car.getCarWithSortedComponents()
                );
    }


}
