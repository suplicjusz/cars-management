package stats_service;

import car_service.CarServiceImpl;
import extensions.CarServiceParameterResolver;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@RequiredArgsConstructor
@ExtendWith(CarServiceParameterResolver.class)
public class CarMileageStatsServiceImplTest {

    private final CarServiceImpl carService;

    public static final double AVG = 146833.33333333334;
    public static final double MAX = 305000.0;
    public static final double MIN = 20500.0;

    @Test
    @DisplayName("when the list of cars is empty")
    public void shouldThrowsIllegalArgumentException() {
        Assertions
                .assertThatThrownBy(() -> CarMileageStatsServiceImpl.of(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("List of cars is null or empty!");
    }

    @Test
    @DisplayName("when the object of CarMileageStatsService is calling the avg method")
    public void shouldReturnDoubleAvg() {
        org.junit.jupiter.api.Assertions
                .assertEquals(
                        AVG,
                        CarMileageStatsServiceImpl.of(carService.getCars()).getAvg()
                );
    }

    @Test
    @DisplayName("when the object of CarMileageStatsService is calling the max method")
    public void shouldReturnDoubleMax() {
        org.junit.jupiter.api.Assertions
                .assertEquals(
                        MAX,
                        CarMileageStatsServiceImpl.of(carService.getCars()).getMax()
                );
    }

    @Test
    @DisplayName("when the object of CarMileageStatsService is calling the min method")
    public void shouldReturnDoubleMin() {
        org.junit.jupiter.api.Assertions
                .assertEquals(
                        MIN,
                        CarMileageStatsServiceImpl.of(carService.getCars()).getMin()
                );
    }


}

