package stats_service;

import car_service.CarServiceImpl;
import extensions.CarServiceParameterResolver;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@ExtendWith(CarServiceParameterResolver.class)
public class CarBigDecimalStatsServiceImplTest {
    private final CarServiceImpl carService;

    //private static final BigDecimal sumContent = new BigDecimal("182500");
    private static final BigDecimal AVG = new BigDecimal("60833,33");
    private static final BigDecimal MAX = new BigDecimal("105000");
    private static final BigDecimal MIN = new BigDecimal("25500");

    @Test
    @DisplayName("when the of method get empty")
    void shouldThrowsIllegalArgumentException() {
        Assertions
                .assertThatThrownBy(() -> CarBigDecimalStatsServiceImpl.of(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("List of cars is null or empty!");
    }
    //w takim wypadku przy null IDE się czepia

    @Test
    @DisplayName("when the object of CarServiceImpl is calling avg method")
    void shouldReturnBigDecimalAvg() {
        Assertions
                .assertThat(CarBigDecimalStatsServiceImpl.of(carService.getCars()).getAvg())
                .isEqualByComparingTo(AVG);
    }
}
