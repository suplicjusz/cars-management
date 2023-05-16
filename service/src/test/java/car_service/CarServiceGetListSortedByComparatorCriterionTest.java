package car_service;


import extensions.CarServiceParameterResolver;
import lombok.RequiredArgsConstructor;
import model.car.Car;
import model.car.CarComparator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import support.CarServiceTestingContent;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@ExtendWith(CarServiceParameterResolver.class)
public class CarServiceGetListSortedByComparatorCriterionTest implements CarServiceTestingContent<Map<Long, List<Car>>> {

    private final CarServiceImpl extensionService;

    @Override
    public Map<Long, List<Car>> getContent() {
        return Map.of(
                1L,
                List.of(
                        carsTestingContent.get(0),
                        carsTestingContent.get(1),
                        carsTestingContent.get(2)
                ),
                2L,
                List.of(
                        carsTestingContent.get(2),
                        carsTestingContent.get(1),
                        carsTestingContent.get(0)
                )
        );
    }

    @Test
    @DisplayName("when comparator is null")
    void shouldThrowsIllegalArgumentException() {
        Assertions
                .assertThatThrownBy(
                        () -> extensionService.getListSortedByComparatorCriterion(
                                null, true)
                )
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Comparator is null!");

    }

    @Test
    @DisplayName("when is returned list sorted by color and ascending of value is required")
    void shouldReturnedListSortedByColorAscending() {
        Assertions
                .assertThat(extensionService.getListSortedByComparatorCriterion(CarComparator.compareByColor, true))
                .containsSequence(getContent().get(1L));


    }

    @Test
    @DisplayName("when is returned list sorted by color and descending of value is required")
    void shouldReturnedListSortedByColorDescending() {
        Assertions
                .assertThat(extensionService.getListSortedByComparatorCriterion(CarComparator.compareByColor, false))
                .containsSequence(getContent().get(2L));

    }


}
